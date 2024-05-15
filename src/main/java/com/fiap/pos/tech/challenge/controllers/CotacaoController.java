package com.fiap.pos.tech.challenge.controllers;

import com.fiap.pos.tech.challenge.controllers.dto.CotacaoDTO;
import com.fiap.pos.tech.challenge.controllers.dto.system.SuccessResponseDTO;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO segundaVia(@NotNull @Valid @PathVariable UUID id, @RequestParam String email) {
        service.enviarCotacao(id, email);

        return new SuccessResponseDTO("Email enviado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO deletarCotacao(@NotNull @Valid @PathVariable UUID id) {
        service.deletarCotacao(id);

        return new SuccessResponseDTO("Cotação excluida com sucesso.");
    }
}
