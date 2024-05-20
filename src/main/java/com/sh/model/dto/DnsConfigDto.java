package com.sh.model.dto;

import lombok.Data;

/**
 * @author 27300
 */
@Data
public class DnsConfigDto {
    private String accessKeyId;
    private String accessKeySecret;
    private String toMail;
    private Integer groupId;
    private String domainName;
    
}
