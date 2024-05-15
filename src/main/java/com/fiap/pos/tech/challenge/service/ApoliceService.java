package com.fiap.pos.tech.challenge.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ApoliceService {
    UUID gerarApolice(UUID numeroCotacao, MultipartFile cotacaoAssinada);
}
