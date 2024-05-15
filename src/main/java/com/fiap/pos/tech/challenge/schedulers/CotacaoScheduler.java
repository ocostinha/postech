package com.fiap.pos.tech.challenge.schedulers;

import com.fiap.pos.tech.challenge.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CotacaoScheduler {

    @Autowired
    private CotacaoService cotacaoService;

    @Scheduled(cron = "0 * * * * ?")
    public void enviarCotacoes() {
        cotacaoService.enviarCotacoesPendentes();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void gerarCotacoes() {
        cotacaoService.gerarCotacoesPendentes();
    }
}
