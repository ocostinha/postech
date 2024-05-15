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
    private DadosEnderecoMapper dadosEnderecoMapper;

    @Autowired
    private DadosPessoaisMapper dadosPessoaisMapper;

    @Autowired
    private DadosVeiculoMapper dadosVeiculoMapper;

    @Mapping(source = "cpf", target = "dadosCadastrais.dadosPessoais.cpf")
    @Mapping(source = "placaVeiculo", target = "dadosVeiculo.placa")
    @Mapping(source = "email", target = "dadosCadastrais.dadosContato.email")
    @Mapping(target = "dataHoraCriacao", ignore = true)
    @Mapping(target = "dataHoraExpiracao", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Cotacao toEntity(CotacaoDTO dto);

    @Mapping(target = "dadosCadastrais", ignore = true)
    @Mapping(target = "dadosVeiculo", ignore = true)
    public abstract Cotacao toEntity(CotacaoEntity db);

    @Mapping(target = "id", defaultExpression = MappingUtils.GENERATE_UUID_EXPRESSION)
    @Mapping(target = "dataHoraCriacao", defaultExpression = MappingUtils.LOCAL_DATE_TIME_NOW)
    @Mapping(target = "status", defaultValue = "COTACAO_SOLICITADA")
    public abstract CotacaoEntity toDb(Cotacao entity,
                                       DadosPessoais dpEntity,
                                       DadosContato dcEntity,
                                       DadosEndereco dmEntity,
                                       DadosVeiculo dvEntity
    );

    @AfterMapping
    protected void enrichEntity(@MappingTarget Cotacao entity, CotacaoEntity db) {
        entity.setDadosCadastrais(new DadosCadastrais());
        entity.getDadosCadastrais().setDadosContato(dadosContatoMapper.toEntity(db));
        entity.getDadosCadastrais().setDadosEndereco(dadosEnderecoMapper.toEntity(db));
        entity.getDadosCadastrais().setDadosPessoais(dadosPessoaisMapper.toEntity(db));
        entity.setDadosVeiculo(dadosVeiculoMapper.toEntity(db));
    }
}
