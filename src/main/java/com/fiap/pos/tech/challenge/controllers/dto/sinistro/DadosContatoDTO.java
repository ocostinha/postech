package com.fiap.pos.tech.challenge.controllers.dto.sinistro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosContatoDTO {
    @NotBlank
    private String email;

    private Integer dddFixo;

    private Long fixo;

    @NotNull
    private Integer dddCelular;

    @NotNull
    private Long celular;

    private Integer dddRecado;

    private Long recado;
}
