package com.fiap.pos.tech.challenge.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosMoradia {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Long cep;
}
