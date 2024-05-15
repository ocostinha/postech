package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.DadosEnderecoDTO;
import com.fiap.pos.tech.challenge.entities.DadosEndereco;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DadosEnderecoMapper {
    DadosEndereco toEntity(CotacaoEntity db);
    DadosEndereco toEntity(DadosEnderecoDTO dto);
}
