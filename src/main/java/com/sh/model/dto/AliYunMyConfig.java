package com.sh.model.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AliYunMyConfig
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/4/1 10:57
 * @Author: SH
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "mail.my-config")
public class AliYunMyConfig {
	private Integer fixedRate;
	private String domainName;
	private Integer groupId;
	
}
