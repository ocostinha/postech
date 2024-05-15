package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Service
public class S3ServiceImpl implements S3Service {
    @Autowired
    private S3Client s3Template;

    @Override
    public void enviar(String bucket, String key, MultipartFile file) {
        try {
            var s3Client = S3Client
                    .builder()
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .region(Region.of("us-east-1"))
                    .endpointOverride(URI.create("http://localhost:4566"))
                    .forcePathStyle(true) // <-- this fixes it.
                    .build();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
