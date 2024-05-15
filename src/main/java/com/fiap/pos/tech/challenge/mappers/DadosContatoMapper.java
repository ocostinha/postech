package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.DadosContatoDTO;
import com.fiap.pos.tech.challenge.entities.DadosContato;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DadosContatoMapper {
    DadosContato toEntity(CotacaoEntity db);
    DadosContato toEntity(DadosContatoDTO dto);
}
