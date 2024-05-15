package com.fiap.pos.tech.challenge.service;

import java.io.File;

public interface EmailService {
    void enviar(String email, File arquivo);
}
