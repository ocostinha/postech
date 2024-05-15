package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.DadosPessoaisDTO;
import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroDTO;
import com.fiap.pos.tech.challenge.entities.Sinistro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DadosEnderecoMapper.class, DadosContatoMapper.class, DadosPessoaisDTO.class})
public interface SinistroMapper {
    @Mapping(target = "status", expression = "CADASTRADO")
    Sinistro toEntity(SinistroDTO dto);
}
