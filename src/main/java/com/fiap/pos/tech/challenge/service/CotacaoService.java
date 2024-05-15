package com.fiap.pos.tech.challenge.service;

import com.fiap.pos.tech.challenge.entities.Cotacao;

import java.util.UUID;

public interface CotacaoService {
    void receberCotacao(Cotacao cotacao);
    Cotacao consultarCotacao(UUID id);
    void enviarCotacoesPendentes();
    void enviarCotacao(UUID id);
    void gerarCotacoesPendentes();
    void aprovarCotacao(UUID id);
}
