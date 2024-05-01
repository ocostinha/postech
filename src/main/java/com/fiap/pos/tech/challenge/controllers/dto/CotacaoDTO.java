package com.fiap.pos.tech.challenge.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoDTO {
    @NotBlank(message = "Por favor, informe o CPF")
    private String cpf;

    @NotBlank(message = "Por favor, informe a placa do veículo")
    private String placaVeiculo;

    @NotBlank(message = "Por favor, informe o seu melhor email")
    private String email;
}
