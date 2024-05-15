package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.mappers.ApoliceMapper;
import com.fiap.pos.tech.challenge.repository.ApoliceRepository;
import com.fiap.pos.tech.challenge.service.ApoliceService;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import com.fiap.pos.tech.challenge.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        final var apolice =
                mapper.toEntity(
                    repository.save(
                        mapper.toDb(
                            mapper.fromCotacao(
                                    cotacaoService.consultarCotacao(numeroCotacao))
                    )));

        s3Service.enviar(BUCKET, gerarNomeDocumento(apolice.getId()), cotacaoAssinada);

        return apolice.getId();
    }

    private String gerarNomeDocumento(final UUID idCotacao) {
        return String.format("cotacao_assinada_%s.pdf", idCotacao);
    }
}
