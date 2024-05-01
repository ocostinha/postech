package com.fiap.pos.tech.challenge.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosCliente {
    private DadosPessoais dadosPessoais;
    private DadosContato dadosContato;
    private DadosMoradia dadosMoradia;
}

