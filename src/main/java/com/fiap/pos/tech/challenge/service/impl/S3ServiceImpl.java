package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class S3ServiceImpl implements S3Service {
    @Autowired
    private S3Client s3Client;

    @Override
    public void enviar(final String bucket, final String key, final MultipartFile file) {
        try {
            PutObjectRequest requisicao = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.putObject(requisicao, RequestBody.fromBytes(file.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public File ler(final String bucket, final String nomeArquivo) {
        try {
            GetObjectRequest requisicao = GetObjectRequest
                    .builder()
                    .bucket(bucket)
                    .key(nomeArquivo)
                    .build();

            var resultado = s3Client.getObject(requisicao);

            File arquivo = new File(nomeArquivo);

            try (FileOutputStream outputStream = new FileOutputStream(arquivo)) {
                outputStream.write(resultado.readAllBytes());
            }

            return arquivo;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    }
}
