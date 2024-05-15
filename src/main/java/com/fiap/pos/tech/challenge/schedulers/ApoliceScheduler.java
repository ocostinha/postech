package com.fiap.pos.tech.challenge.schedulers;

import com.fiap.pos.tech.challenge.service.ApoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApoliceScheduler {
    @Autowired
    private ApoliceService apoliceService;

    @Scheduled(cron = "0 * * * * ?")
    public void enviarApolices() {
        apoliceService.enviarApolicesPendentes();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void enviarDestratos() {
        apoliceService.enviarDestratos();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void encerrarApolices() {
        apoliceService.encerrarApolices();
    }
}
