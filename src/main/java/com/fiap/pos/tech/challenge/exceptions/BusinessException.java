package com.fiap.pos.tech.challenge.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
