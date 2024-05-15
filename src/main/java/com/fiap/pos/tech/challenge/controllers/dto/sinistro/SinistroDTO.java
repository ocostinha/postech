package com.fiap.pos.tech.challenge.controllers.dto.sinistro;

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
public class SinistroDTO {
    private UUID idApolice;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private DadosEnderecoDTO enderecoSinistro;
    private DadosContatoDTO dadosContatoSinistrado;
    private DadosPessoaisDTO dadosPessoaisSinistrado;
}
