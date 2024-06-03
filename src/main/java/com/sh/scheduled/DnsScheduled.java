package com.sh.scheduled;

import com.sh.model.dto.AliYunDnsProperties;
import com.sh.service.impl.ManageIpv6ServiceImpl;
import com.sh.utils.BackendWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 27300
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DnsScheduled {
    private final AliYunDnsProperties aliYunDnsProperties;
    private final ManageIpv6ServiceImpl manageIpv6ServiceImpl;
    
    @Scheduled(fixedRate = 300000)
    public void updateDns(){
        if (aliYunDnsProperties.getEnable()) {
            BackendWorker.submit(manageIpv6ServiceImpl::updateDns);
        }
    }
}
