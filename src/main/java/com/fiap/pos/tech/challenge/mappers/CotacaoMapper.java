package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.controllers.dto.CotacaoDTO;
import com.fiap.pos.tech.challenge.entities.*;
import com.fiap.pos.tech.challenge.mappers.utils.MappingUtils;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class CotacaoMapper {
    @Autowired
    private DadosContatoMapper dadosContatoMapper;

    @Autowired
    private DadosMoradiaMapper dadosMoradiaMapper;

    @Autowired
    private DadosPessoaisMapper dadosPessoaisMapper;

    @Autowired
    private DadosVeiculoMapper dadosVeiculoMapper;

    @Mapping(source = "cpf", target = "dadosCliente.dadosPessoais.cpf")
    @Mapping(source = "placaVeiculo", target = "dadosVeiculo.placa")
    @Mapping(source = "email", target = "dadosCliente.dadosContato.email")
    public abstract Cotacao toEntity(CotacaoDTO dto);

    public abstract Cotacao toEntity(CotacaoEntity db);

    @Mapping(target = "id", defaultExpression = MappingUtils.GENERATE_UUID_EXPRESSION)
    @Mapping(target = "dataHoraCriacao", defaultExpression = MappingUtils.LOCAL_DATE_TIME_NOW)
    @Mapping(target = "status", defaultValue = "COTACAO_SOLICITADA")
    public abstract CotacaoEntity toDb(Cotacao entity,
                                       DadosPessoais dpEntity,
                                       DadosContato dcEntity,
                                       DadosMoradia dmEntity,
                                       DadosVeiculo dvEntity
    );

    @AfterMapping
    protected void enrichEntity(@MappingTarget Cotacao entity, CotacaoEntity db) {
        entity.setDadosCliente(new DadosCliente());
        entity.getDadosCliente().setDadosContato(dadosContatoMapper.toEntity(db));
        entity.getDadosCliente().setDadosMoradia(dadosMoradiaMapper.toEntity(db));
        entity.getDadosCliente().setDadosPessoais(dadosPessoaisMapper.toEntity(db));
        entity.setDadosVeiculo(dadosVeiculoMapper.toEntity(db));
    }
}
