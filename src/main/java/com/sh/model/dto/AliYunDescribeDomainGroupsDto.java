package com.sh.model.dto;

import com.sh.model.enums.CustomMapKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 27300
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliYunDescribeDomainGroupsDto extends AliYunBaseDto{
    // 语言
    @CustomMapKey("Lang")
    private String lang;
    
    // 组名关键字
    @CustomMapKey("KeyWord")
    private String keyWord;
    
    // 当前页数
    @CustomMapKey("PageNumber")
    private long pageNumber;
    
    // 每页大小
    @CustomMapKey("PageSize")
    private long pageSize;
}
