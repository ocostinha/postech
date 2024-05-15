package com.fiap.pos.tech.challenge.service;

import com.fiap.pos.tech.challenge.entities.Apolice;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ApoliceService {
    UUID gerarApolice(UUID numeroCotacao, MultipartFile cotacaoAssinada);
    void enviarApolicesPendentes();
    void cancelarApolice(UUID id);
    void enviarDestratos();
    void encerrarApolices();
    Apolice obterApolicePelaCotacao(UUID idCotacao);
}
