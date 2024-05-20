package com.sh.model.dto;

import com.sh.model.enums.CustomMapKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AliYunDescribeDomainRecordsDto
 * @Description: 查询可授权 APP 列表
 * @Version: 1.0.0
 * @Date: 2024/3/5 15:17
 * @Author: SH
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliYunDescribeDomainRecordsDto extends AliYunBaseDto{
    
    @CustomMapKey("Lang")
    private String Lang;
    @CustomMapKey("DomainName")
    private String DomainName;
    @CustomMapKey("PageNumber")
    private Integer PageNumber;
    @CustomMapKey("PageSize")
    private Integer PageSize;
    @CustomMapKey("KeyWord")
    private String KeyWord;
    @CustomMapKey("RRKeyWord")
    private String RRKeyWord;
    @CustomMapKey("TypeKeyWord")
    private String TypeKeyWord;
    @CustomMapKey("ValueKeyWord")
    private String ValueKeyWord;
    @CustomMapKey("OrderBy")
    private String OrderBy;
    @CustomMapKey("Direction")
    private String Direction;
    @CustomMapKey("SearchMode")
    private String SearchMode;
    @CustomMapKey("GroupId")
    private Integer GroupId;
    @CustomMapKey("Type")
    private String Type;
    @CustomMapKey("Line")
    private String Line;
    @CustomMapKey("Status")
    private String Status;

}
