package com.fiap.pos.tech.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3ClientConfig {
    @Bean
    @Primary
    public S3Client s3Client() {
        return S3Client
                .builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.of("us-east-1"))
                .endpointOverride(URI.create("http://localhost:4566"))
                .forcePathStyle(true)
                .build();
    }
}
