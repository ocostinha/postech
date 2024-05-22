package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.entities.Sinistro;
import com.fiap.pos.tech.challenge.enums.StatusSinistroEnum;
import com.fiap.pos.tech.challenge.exceptions.BusinessException;
import com.fiap.pos.tech.challenge.mappers.SinistroMapper;
import com.fiap.pos.tech.challenge.repository.ApoliceRepository;
import com.fiap.pos.tech.challenge.repository.SinistroRepository;
import com.fiap.pos.tech.challenge.service.SinistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SinistroServiceImpl implements SinistroService {
    @Autowired
    private SinistroRepository repository;

    @Autowired
    private SinistroMapper mapper;

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Override
    public UUID cadastrarSinistro(final Sinistro sinistro) {
        return repository.save(
                mapper.toDb(
                        sinistro,
                        sinistro.getDadosPessoaisSinistrado(),
                        sinistro.getDadosContatoSinistrado(),
                        sinistro.getEnderecoSinistro()))
                .getId();

        //TODO enviar para o SNS o novo sinistro
    }

    @Override
    public void enviarSinistro(final UUID id, final String email) {
        final var sinistro = repository.getReferenceById(id);

        if (email == null) {
            final var apolice = apoliceRepository.getReferenceById(sinistro.getIdApolice());

            //TODO enviar apolice para o email informado
        } else {
            //TODO enviar apolice para o email do cadastro
        }
    }

    @Override
    public void cancelarSinistro(final UUID id) {
        final var sinistro = repository.getReferenceById(id);

        sinistro.setStatus(StatusSinistroEnum.CANCELADO);

        repository.save(sinistro);

        //TODO enviar atualização por email
    }

    @Override
    public void recorrerDecisaoSinistro(final UUID id, final String motivo) {
        final var sinistro = repository.getReferenceById(id);

        if (!sinistro.getStatus().equals(StatusSinistroEnum.FINALIZADO)) {
            throw new BusinessException("Apenas sinistros sem apelo podem ser julgados");
        }

        sinistro.setMotivoPrimeiroApelo(motivo);
        sinistro.setStatus(StatusSinistroEnum.CONTESTADO);

        repository.save(sinistro);

        //TODO enviar para o SNS de apelo
    }

    @Override
    public void recorrerDecisaoApelo(final UUID id, final String motivo) {
        final var sinistro = repository.getReferenceById(id);

        if (!sinistro.getStatus().equals(StatusSinistroEnum.FINALIZADO)) {
            throw new BusinessException("Apenas sinistros sem apelo podem ser julgados");
        }

        sinistro.setMotivoSegundoApelo(motivo);
        sinistro.setStatus(StatusSinistroEnum.CONTESTADO);

        repository.save(sinistro);

        //TODO enviar atualização por email
    }

    @Override
    public void julgarSinistro(final UUID id, final String decisao) {
        final var sinistro = repository.getReferenceById(id);

        if (!sinistro.getStatus().equals(StatusSinistroEnum.CADASTRADO)) {
            throw new BusinessException("Apenas sinistros sem apelo podem ser julgados");
        }

        sinistro.setDecisaoSinistro(decisao);
        sinistro.setStatus(StatusSinistroEnum.FINALIZADO);

        repository.save(sinistro);

        //TODO enviar atualização por email
    }

    @Override
    public void julgarApeloDecisaoSinistro(final UUID id, final String decisao) {
        final var sinistro = repository.getReferenceById(id);

        if (sinistro.getStatus().equals(StatusSinistroEnum.CONTESTADO)) {
            throw new BusinessException("Apenas sinistros contestado ser julgados");
        }

        sinistro.setDecisaoApelo(decisao);
        sinistro.setStatus(StatusSinistroEnum.FINALIZADO);

        repository.save(sinistro);

        //TODO enviar atualização por email
    }

    @Override
    public void julgamentoFinal(final UUID id, final String decisao) {
        final var sinistro = repository.getReferenceById(id);

        if (sinistro.getStatus().equals(StatusSinistroEnum.CONTESTADO)) {
            throw new BusinessException("Apenas sinistros contestado ser julgados");
        }

        sinistro.setDecisaoSegundoApelo(decisao);
        sinistro.setStatus(StatusSinistroEnum.ENCERRADO);

        repository.save(sinistro);

        //TODO enviar atualização por email
    }
}
