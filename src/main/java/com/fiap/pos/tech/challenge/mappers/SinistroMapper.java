package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.sinistro.DadosPessoaisDTO;
import com.fiap.pos.tech.challenge.controllers.dto.sinistro.SinistroDTO;
import com.fiap.pos.tech.challenge.entities.DadosContato;
import com.fiap.pos.tech.challenge.entities.DadosEndereco;
import com.fiap.pos.tech.challenge.entities.DadosPessoais;
import com.fiap.pos.tech.challenge.entities.Sinistro;
import com.fiap.pos.tech.challenge.mappers.utils.MappingUtils;
import com.fiap.pos.tech.challenge.repository.entity.SinistroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DadosEnderecoMapper.class, DadosContatoMapper.class, DadosPessoaisDTO.class})
public interface SinistroMapper {
    @Mapping(target = "status", constant = "CADASTRADO")
    @Mapping(target = "motivoPrimeiroApelo", ignore = true)
    @Mapping(target = "motivoSegundoApelo", ignore = true)
    Sinistro toEntity(SinistroDTO dto);


    @Mapping(target = "decisaoSinistro", ignore = true)
    @Mapping(target = "decisaoApelo", ignore = true)
    @Mapping(target = "decisaoSegundoApelo", ignore = true)
    @Mapping(target = "id", expression = MappingUtils.GENERATE_UUID_EXPRESSION)
    @Mapping(target = "dataHoraCriacao", expression = MappingUtils.LOCAL_DATE_TIME_NOW)
    SinistroEntity toDb(Sinistro entity,
                        DadosPessoais dpEntity,
                        DadosContato dcEntity,
                        DadosEndereco dmEntity);
}
