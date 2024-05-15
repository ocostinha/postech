package com.fiap.pos.tech.challenge.controllers;

import com.fiap.pos.tech.challenge.controllers.dto.SuccessResponseDTO;
import com.fiap.pos.tech.challenge.service.ApoliceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/apolice")
public class ApoliceController {
    @Autowired
    private ApoliceService apoliceService;

    @PostMapping(value = "/criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO gerarApolice(
            @RequestParam(name = "numero_cotacao") @NotNull @Valid UUID numeroCotacao,
            @RequestParam(name = "cotacao_assinada") MultipartFile cotacaoAssinada) {
        return new SuccessResponseDTO(
                String.format("Apólice %s gerada com sucesso. " +
                                "Em breve o(a) senhor(a) receberá por email sua apólice",
                        apoliceService.gerarApolice(numeroCotacao, cotacaoAssinada))
        );
    }

    @PutMapping(value = "/cancelar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SuccessResponseDTO cancelarApolice(@PathVariable @NotNull @Valid UUID id) {
        apoliceService.cancelarApolice(id);

        return new SuccessResponseDTO(
                String.format("Apólice %s cancelada com sucesso. Em breve o(a) senhor(a) receberá " +
                        "por email seu destrato", id)
        );
    }
}
