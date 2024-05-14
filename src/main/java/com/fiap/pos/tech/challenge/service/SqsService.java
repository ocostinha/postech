package com.fiap.pos.tech.challenge.service;

public interface SqsService {
    void enviar(String url, String message);
}
