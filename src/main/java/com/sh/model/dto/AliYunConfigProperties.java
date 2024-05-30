package com.sh.model.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 27300
 */

@Data
@ConfigurationProperties(
        prefix = "aliyun"
)
public class AliYunConfigProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
}
