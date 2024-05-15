package com.fiap.pos.tech.challenge.entities;

import com.fiap.pos.tech.challenge.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Apolice {
    private UUID id;
    private UUID idCotacao;
    private DadosCliente dadosCliente;
    private DadosVeiculo dadosVeiculo;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraExpiracao;
    private StatusEnum status;
}
