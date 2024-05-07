package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.entities.DadosVeiculo;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DadosVeiculoMapper {
    DadosVeiculo toEntity(CotacaoEntity db);
}
