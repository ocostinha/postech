package com.fiap.pos.tech.challenge.controllers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroDTO;
import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroDecisaoDTO;
import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroMotivoDTO;
import com.fiap.pos.tech.challenge.controllers.dto.system.SuccessResponseDTO;
import com.fiap.pos.tech.challenge.mappers.SinistroMapper;
import com.fiap.pos.tech.challenge.service.SinistroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sinistro")
public class SinistroController {
    private final static String RESPOSTA_PADRAO =
            "Solicitação efetuada com sucesso. Em breve o(a) senhor(a) receberá por email o sinistro (%s)";

    @Autowired
    private SinistroMapper mapper;

    @Autowired
    private SinistroService service;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO receberSinistro(@Valid @RequestBody SinistroDTO dto) {
        return new SuccessResponseDTO(
                "Sinistro cadastrado com sucesso. Chave solicitação " +
                        service.cadastrarSinistro(mapper.toEntity(dto))
        );
    }

    @PostMapping("/{id}/julgar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO julgarSinistro(
            @PathVariable @NotNull @Valid UUID id, @Valid @RequestBody SinistroDecisaoDTO dto) {
        service.julgarSinistro(id , dto.getDecisao());

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @PostMapping("/{id}/recorrer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO recorrerSinistro(
            @PathVariable @NotNull @Valid UUID id, @Valid @RequestBody SinistroMotivoDTO dto) {
        service.recorrerDecisaoSinistro(id , dto.getMotivo());

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @PostMapping("/{id}/julgar/apelo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO julgarApeloSinistro(
            @PathVariable @NotNull @Valid UUID id, @Valid @RequestBody SinistroDecisaoDTO dto) {
        service.julgarApeloDecisaoSinistro(id , dto.getDecisao());

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @PostMapping("/{id}/recorrer/apelo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO recorrerAoGerenteSinistro(
            @PathVariable @NotNull @Valid UUID id, @Valid @RequestBody SinistroMotivoDTO dto) {
        service.recorrerDecisaoApelo(id , dto.getMotivo());

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @PostMapping("/{id}/julgar/apelo/final")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO julgarApeloFinalSinistro(
            @PathVariable @NotNull @Valid UUID id, @Valid @RequestBody SinistroDecisaoDTO dto) {
        service.julgamentoFinal(id , dto.getDecisao());

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO consultarSinistro(
            @PathVariable @NotNull @Valid UUID id, @RequestParam String email
    ) {
        service.enviarSinistro(id, email);

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO cancelarSinistro(@PathVariable @NotNull @Valid UUID id) {
        service.cancelarSinistro(id);

        return new SuccessResponseDTO(String.format(RESPOSTA_PADRAO, id));
    }
}
