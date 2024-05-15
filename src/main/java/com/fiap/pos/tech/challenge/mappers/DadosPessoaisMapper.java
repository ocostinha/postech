package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.DadosPessoaisDTO;
import com.fiap.pos.tech.challenge.entities.DadosPessoais;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DadosPessoaisMapper {
    DadosPessoais toEntity(CotacaoEntity db);

    @Mapping(target = "nomePai", ignore = true)
    @Mapping(target = "nomeMae", ignore = true)
    DadosPessoais toEntity(DadosPessoaisDTO dto);
}
