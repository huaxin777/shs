package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: AliYunBeasDto
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 15:40
 * @Author: SH
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliYunBaseDto {
    private String action;
}
