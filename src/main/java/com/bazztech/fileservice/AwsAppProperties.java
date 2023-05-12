package com.bazztech.fileservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bazz
 * May 07 2023
 * 13:34
 */
@Component
@ConfigurationProperties(prefix = "app.aws", ignoreInvalidFields = true)
@Getter
@Setter
public class AwsAppProperties {
    private String bucketName;
    private String accessKeyId;
    private String secretAccessKey;
    private String s3Region;
}
