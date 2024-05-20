package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @ClassName: AliYunAddDomainRecordDto
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 15:11
 * @Author: SH
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliYunAddDomainRecordDto extends AliYunBaseDto{
    private String Lang;
    private String UserClientIp;
    private String DomainName;
    private String RR;
    private String Type;
    private String Value;
    private String TTL;
    private String Priority;
    private String Line;
}
