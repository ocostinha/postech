package com.fiap.pos.tech.challenge.service;

import com.fiap.pos.tech.challenge.entities.Sinistro;

import java.util.UUID;

public interface SinistroService {
    UUID cadastrarSinistro(Sinistro sinistro);
    void enviarSinistro(UUID id, String email);
    void cancelarSinistro(UUID id);
    void recorrerDecisaoSinistro(UUID id, String motivo);
    void recorrerDecisaoApelo(UUID id, String motivo);
    void julgarSinistro(UUID id, String decisao);
    void julgarApeloDecisaoSinistro(UUID id, String decisao);
    void julgamentoFinal(UUID id, String decisao);
}
