package com.fiap.pos.tech.challenge.controllers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroDTO;
import com.fiap.pos.tech.challenge.controllers.dto.system.SuccessResponseDTO;
import com.fiap.pos.tech.challenge.mappers.SinistroMapper;
import com.fiap.pos.tech.challenge.service.SinistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sinistro")
public class SinistroController {
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
}
