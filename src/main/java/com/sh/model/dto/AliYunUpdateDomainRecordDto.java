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
    @CustomMapKey("Lang")
    private String Lang;
    
    @CustomMapKey("UserClientIp")
    private String UserClientIp;
    
    @CustomMapKey("RecordId")
    private String RecordId;
    
    @CustomMapKey("RR")
    private String RR;
    
    @CustomMapKey("Type")
    private String Type;
    
    @CustomMapKey("Value")
    private String Value;
    
    @CustomMapKey("TTL")
    private String TTL;
    
    @CustomMapKey("Priority")
    private String Priority;
    
    @CustomMapKey("Line")
    private String Line;
}
