package com.sh.model.dto;

import com.sh.model.enums.CustomMapKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AliYunUpdateDomainRecordRemarkDto
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 17:24
 * @Author: SH
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliYunUpdateDomainRecordRemarkDto extends AliYunBaseDto{
    @CustomMapKey("Lang")
    private String Lang;
    @CustomMapKey("UserClientIp")
    private String UserClientIp;
    @CustomMapKey("RecordId")
    private String RecordId;
    @CustomMapKey("Remark")
    private String Remark;
}
