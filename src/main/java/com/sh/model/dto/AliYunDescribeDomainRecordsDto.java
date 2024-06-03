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
    private String action = "DescribeDomainRecords";
    @CustomMapKey("Lang")
    private String lang;
    @CustomMapKey("DomainName")
    private String domainName;
    @CustomMapKey("PageNumber")
    private Integer pageNumber;
    @CustomMapKey("PageSize")
    private Integer pageSize;
    @CustomMapKey("KeyWord")
    private String keyWord;
    @CustomMapKey("RRKeyWord")
    private String rRKeyWord;
    @CustomMapKey("TypeKeyWord")
    private String typeKeyWord;
    @CustomMapKey("ValueKeyWord")
    private String valueKeyWord;
    @CustomMapKey("OrderBy")
    private String oOrderBy;
    @CustomMapKey("Direction")
    private String direction;
    @CustomMapKey("SearchMode")
    private String searchMode;
    @CustomMapKey("GroupId")
    private Integer groupId;
    @CustomMapKey("Type")
    private String type;
    @CustomMapKey("Line")
    private String line;
    @CustomMapKey("Status")
    private String status;

}
