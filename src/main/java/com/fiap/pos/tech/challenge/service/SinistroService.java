package com.fiap.pos.tech.challenge.service;

import com.fiap.pos.tech.challenge.entities.Sinistro;

import java.util.UUID;

public interface SinistroService {
    UUID cadastrarSinistro(Sinistro sinistro);
}
