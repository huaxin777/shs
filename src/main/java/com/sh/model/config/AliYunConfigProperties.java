package com.sh.model.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 27300
 */

@Data
@Configuration
@ConfigurationProperties(
        prefix = "aliyun"
)
public class AliYunConfigProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
}
