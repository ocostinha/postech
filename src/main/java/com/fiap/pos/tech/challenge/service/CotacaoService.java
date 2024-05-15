package com.fiap.pos.tech.challenge.service;

import com.fiap.pos.tech.challenge.entities.Cotacao;

import java.util.UUID;

public interface CotacaoService {
    Cotacao receberCotacao(Cotacao cotacao);
    Cotacao consultarCotacao(UUID id);
}
