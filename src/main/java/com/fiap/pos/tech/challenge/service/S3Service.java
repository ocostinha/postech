package com.fiap.pos.tech.challenge.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface S3Service {
    void enviar(String bucket, String key, MultipartFile file);

    File ler(String bucket, String nomeArquivo);
}
