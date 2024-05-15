package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.exceptions.BusinessException;
import com.fiap.pos.tech.challenge.mappers.ApoliceMapper;
import com.fiap.pos.tech.challenge.repository.ApoliceRepository;
import com.fiap.pos.tech.challenge.service.ApoliceService;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import com.fiap.pos.tech.challenge.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ApoliceServiceImpl implements ApoliceService {
    @Autowired
    private S3Service s3Service;

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private ApoliceMapper mapper;

    @Autowired
    private ApoliceRepository repository;

    private static final String BUCKET = "documentos";

    @Override
    public UUID gerarApolice(final UUID numeroCotacao, final MultipartFile cotacaoAssinada) {
        var cotacao =  cotacaoService.consultarCotacao(numeroCotacao);

        if (!cotacao.getStatus().equals(StatusEnum.COTACAO_ENVIADA)) {
            throw new BusinessException("Apenas cotações enviadas podem ser convertidas em apólices");
        }

        final var apolice =
                mapper.toEntity(
                    repository.save(
                        mapper.toDb(
                            mapper.fromCotacao(
                                    cotacao
                            ))));

        s3Service.enviar(BUCKET, gerarNomeDocumentoCotacaoAssinada(apolice.getId()), cotacaoAssinada);

        cotacaoService.aprovarCotacao(cotacao.getId());

        return apolice.getId();
    }

    @Override
    public void enviarApolicesPendentes() {
        repository.findByStatus(StatusEnum.APOLICE_SOLICITADA).forEach(apolice -> {
            //TODO criar o arquivo de apolice, salvar no S3, enviar por email a apolice para o email
            // cadastrado na entidade apolice e alterar o status para APOLICE_EMITIDA
        });
    }

    @Override
    public void cancelarApolice(final UUID id) {
        final var apolice = repository.getReferenceById(id);

        apolice.setStatus(StatusEnum.APOLICE_CANCELADA);

        repository.save(apolice);
    }

    @Override
    public void enviarDestratos() {
        repository.findByStatus(StatusEnum.APOLICE_CANCELADA).forEach(apolice -> {
            //TODO criar o arquivo de destrato, salvar no s3, enviar por email e alterar o
            // status para DESTRATO_ENVIADO
        });
    }

    @Override
    public void encerrarApolices() {
        repository.findByStatusAndDataHoraExpiracaoBefore(
            StatusEnum.APOLICE_EMITIDA, LocalDateTime.now())
                .forEach(apolice -> {
                    apolice.setStatus(StatusEnum.APOLICE_ENCERRADA);

                    //TODO enviar email de finalização de contrato

                    repository.save(apolice);
                });
    }

    private String gerarNomeDocumentoCotacaoAssinada(final UUID idCotacao) {
        return String.format("cotacao_assinada_%s.pdf", idCotacao);
    }

    private String gerarNomeDocumentoApolice(final UUID idCotacao) {
        return String.format("apolice_%s.pdf", idCotacao);
    }
}
