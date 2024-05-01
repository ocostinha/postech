package com.fiap.pos.tech.challenge.controllers;

import com.fiap.pos.tech.challenge.controllers.dto.CotacaoDTO;
import com.fiap.pos.tech.challenge.controllers.dto.SuccessResponseDTO;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {
    @Autowired
    private CotacaoMapper mapper;

    @Autowired
    private CotacaoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO receberCotacao(@Valid @RequestBody CotacaoDTO dto) {
        service.receberCotacao(mapper.toEntity(dto));

        return new SuccessResponseDTO(
                "Solicitação recebida com sucesso, em breve enviaremos via email a cotação do seu seguro"
        );
    }
}
