package com.sh.model.dto;

import com.sh.model.enums.CustomMapKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName: AliYunUpdateDomainRecordDto
 * @Description: 修改域名解析记录
 * @Version: 1.0.0
 * @Date: 2024/3/5 17:43
 * @Author: SH
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AliYunUpdateDomainRecordDto extends AliYunBaseDto{
    private String action = "UpdateDomainRecord";
    
    @CustomMapKey("Lang")
    private String lang;
    
    @CustomMapKey("UserClientIp")
    private String userClientIp;
    
    @CustomMapKey("RecordId")
    private String recordId;
    
    @CustomMapKey("RR")
    private String rR;
    
    @CustomMapKey("Type")
    private String type;
    
    @CustomMapKey("Value")
    private String value;
    
    @CustomMapKey("TTL")
    private String tTL;
    
    @CustomMapKey("Priority")
    private String priority;
    
    @CustomMapKey("Line")
    private String line;
}
