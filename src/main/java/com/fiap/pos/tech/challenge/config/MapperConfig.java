package com.fiap.pos.tech.challenge.config;

import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {
    @Bean
    @Primary
    public CotacaoMapper cotacaoMapper() { return new CotacaoMapperImpl(); }
}
