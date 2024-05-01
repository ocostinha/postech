package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.CotacaoDTO;
import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CotacaoMapper {
    @Mapping(source = "cpf", target = "dadosCliente.dadosPessoais.cpf")
    @Mapping(source = "placaVeiculo", target = "dadosVeiculo.placa")
    @Mapping(source = "email", target = "dadosCliente.dadosContato.email")
    Cotacao toEntity(CotacaoDTO dto);

    Cotacao toEntity(CotacaoEntity db);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    CotacaoEntity toDb(Cotacao entity);
}
