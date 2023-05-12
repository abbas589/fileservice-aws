package com.bazztech.fileservice.integration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.bazztech.fileservice.AwsAppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bazz
 * May 11 2023
 * 21:13
 */
@Configuration
@RequiredArgsConstructor
public class AwsConfiguration {

    private final AwsAppProperties awsAppProperties;


    @Bean
    public AmazonS3 s3Client(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAppProperties.getAccessKeyId(), awsAppProperties.getSecretAccessKey());
        return AmazonS3ClientBuilder.standard().withRegion(awsAppProperties.getS3Region())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
