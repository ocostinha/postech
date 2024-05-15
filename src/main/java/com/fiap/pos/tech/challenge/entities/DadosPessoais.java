package com.fiap.pos.tech.challenge.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPessoais {
    private String nome;
    private String sobrenome;
    private String cpf;
    private Long cnh;
    private String nomeMae;
    private String nomePai;
}
