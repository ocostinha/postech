package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.entities.DadosMoradia;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DadosMoradiaMapper {
    DadosMoradia toEntity(CotacaoEntity db);
}
