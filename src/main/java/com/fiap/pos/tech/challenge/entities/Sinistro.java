package com.fiap.pos.tech.challenge.entities;

import com.fiap.pos.tech.challenge.enums.StatusSinistroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sinistro {
    private UUID idApolice;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private DadosEndereco enderecoSinistro;
    private DadosContato dadosContatoSinistrado;
    private DadosPessoais dadosPessoaisSinistrado;
    private StatusSinistroEnum status;
    private String motivoPrimeiroApelo;
    private String motivoSegundoApelo;
}
