package com.fiap.pos.tech.challenge.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    void enviar(String bucket, String key, MultipartFile file);
}
