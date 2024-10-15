package com.sh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.alidns20150109.models.DescribeDomainRecordsResponse;
import com.aliyun.alidns20150109.models.DescribeDomainRecordsResponseBody;
import com.aliyun.alidns20150109.models.UpdateDomainRecordRemarkResponse;
import com.sh.model.config.AliYunDnsProperties;
import com.sh.model.dto.AliYunDescribeDomainRecordsDto;
import com.sh.model.dto.AliYunUpdateDomainRecordDto;
import com.sh.model.dto.DnsConfigDto;
import com.sh.service.ManageIpv6Service;
import com.sh.utils.AliYunUtil;
import com.sh.utils.DateUtils;
import com.sh.utils.Ipv6Util;
import com.sh.utils.SendMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 27300
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageIpv6ServiceImpl implements ManageIpv6Service {
    
    private final SendMail sendMail;
    private final AliYunDnsProperties aliYunDnsProperties;
    private static final String DNS_TYPE = "AAAA";
    
    public void updateDns(){
        DateUtils.getBeginDayOfTomorrow();
        log.info("1. 开始查询/更新ipv6");
        String ipv6Address  = Ipv6Util.getLocalIpv6AddressByHttp();
        log.info("2. 获取到的本地ipv6: {}", ipv6Address);
        for (DnsConfigDto dnsConfig : aliYunDnsProperties.getDnsConfig()) {
            try {
                List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list = getAliYunDnsList(dnsConfig);
                log.info("3. 查询到的DNS记录id: {}", list);
                list.removeIf(next -> StrUtil.equals(next.getValue(), ipv6Address));

                if (list.isEmpty()){
                    log.info("ipv6未变化: {}", ipv6Address);
                    continue ;
                } else {
                    log.info("ipv6变动: {}", ipv6Address);
                    update(list, ipv6Address,dnsConfig);
                    log.info("4. 修改DNS记录: {}", list);
                }
            } catch (Exception e) {
                log.error(e.toString());
                sendMail.sendTextMailMessage(dnsConfig.getToMail(),"ipv6变化: ",ipv6Address + "   阿里云修改结果: 失败---" + e);
            }
            //发送邮件
            sendMail.sendTextMailMessage(dnsConfig.getToMail(),"ipv6变化: ",ipv6Address + "   阿里云修改结果: 成功" );
            log.info("5. 发送邮件: {}", ipv6Address);
        }
    }
    
    private static void update(List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list, String ipv6Address,DnsConfigDto dnsConfig) {
        AliYunUpdateDomainRecordDto updateDto = new AliYunUpdateDomainRecordDto()
                .setValue(ipv6Address)
                .setType(DNS_TYPE);
        for (DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord dto : list) {
            updateDto.setLine(dto.getLine());
            updateDto.setRecordId(dto.getRecordId());
            updateDto.setRR(dto.getRR());
            AliYunUtil.sendPost(updateDto, UpdateDomainRecordRemarkResponse.class,dnsConfig.getAccessKeyId(),dnsConfig.getAccessKeySecret());
        }
    }
    
    @NotNull
    private static List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> getAliYunDnsList(DnsConfigDto dnsConfig) {
        AliYunDescribeDomainRecordsDto dto = new AliYunDescribeDomainRecordsDto();
        dto.setDomainName(dnsConfig.getDomainName());
        dto.setGroupId(dnsConfig.getGroupId());
        Optional<DescribeDomainRecordsResponse> option = AliYunUtil.sendPost(dto, DescribeDomainRecordsResponse.class,dnsConfig.getAccessKeyId(),dnsConfig.getAccessKeySecret());
        return new ArrayList<>(option.map(DescribeDomainRecordsResponse::getBody)
                .map(DescribeDomainRecordsResponseBody::getDomainRecords)
                .map(DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecords::getRecord)
                .orElseThrow(() -> new RuntimeException("查询的阿里云DNS解析为空")));
    }
}
