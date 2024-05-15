package com.fiap.pos.tech.challenge.mappers;

import com.fiap.pos.tech.challenge.entities.Apolice;
import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.mappers.utils.MappingUtils;
import com.fiap.pos.tech.challenge.repository.entity.ApoliceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ApoliceMapper {
    @Mapping(source = "id", target = "idCotacao")
    @Mapping(target = "id", expression = MappingUtils.GENERATE_UUID_EXPRESSION)
    @Mapping(target = "dataHoraCriacao", expression = MappingUtils.LOCAL_DATE_TIME_NOW)
    @Mapping(target = "dataHoraExpiracao", expression = MappingUtils.LOCAL_DATE_TIME_PLUS_YEAR)
    @Mapping(target = "status", constant = "APOLICE_SOLICITADA")
    public abstract Apolice fromCotacao(Cotacao cotacao);
    public abstract ApoliceEntity toDb(Apolice entity);

    public abstract Apolice toEntity(ApoliceEntity db);
}
