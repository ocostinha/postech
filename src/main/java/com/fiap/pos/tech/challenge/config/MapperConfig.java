package com.fiap.pos.tech.challenge.config;

import com.fiap.pos.tech.challenge.mappers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {
    @Bean
    @Primary
    public CotacaoMapper cotacaoMapper() { return new CotacaoMapperImpl(); }

    @Bean
    @Primary
    public DadosMoradiaMapper dadosMoradiaMapper() { return new DadosMoradiaMapperImpl(); }

    @Bean
    @Primary
    public DadosContatoMapper dadosContatoMapper() { return new DadosContatoMapperImpl(); }

    @Bean
    @Primary
    public DadosPessoaisMapper dadosPessoaisMapper() { return new DadosPessoaisMapperImpl(); }
}
