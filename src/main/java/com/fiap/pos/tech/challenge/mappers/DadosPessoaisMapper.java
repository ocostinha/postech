package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.entities.DadosPessoais;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DadosPessoaisMapper {
    DadosPessoais toEntity(CotacaoEntity db);
}
