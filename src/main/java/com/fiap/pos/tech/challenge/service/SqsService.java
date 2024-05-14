package com.fiap.pos.tech.challenge.service;

public interface SqsService {
    void send(String url, String message);
}
