package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.repository.CotacaoRepository;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import com.fiap.pos.tech.challenge.service.S3Service;
import com.fiap.pos.tech.challenge.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CotacaoServiceImpl implements CotacaoService {
    @Autowired
    private CotacaoRepository repository;

    @Autowired
    private CotacaoMapper mapper;

    @Autowired
    private SqsService sqsService;

    @Autowired
    private S3Service s3Service;

    @Value("${sqs.queueName.dados-veiculo}")
    private String sqsDadosVeiculo;

    @Value("${sqs.queueName.dados-pessoais}")
    private String sqsDadosPessoais;

    private static final String BUCKET = "documentos";

    @Override
    public void receberCotacao(Cotacao cotacao) {
        CotacaoEntity cotacaoExistente = obterCotacaoExistente(cotacao);

        if (cotacaoExistente != null) {
            mapper.toEntity(cotacaoExistenteStrategy(cotacaoExistente));
        }

        CotacaoEntity novaCotacao = repository.save(
                mapper.toDb(
                        cotacao,
                        cotacao.getDadosCliente().getDadosPessoais(),
                        cotacao.getDadosCliente().getDadosContato(),
                        cotacao.getDadosCliente().getDadosMoradia(),
                        cotacao.getDadosVeiculo()
                )
        );

        enfileirarConsultas(cotacao);
    }

    @Override
    public Cotacao consultarCotacao(UUID id) {
        return mapper.toEntity(repository.getReferenceById(id));
    }

    @Override
    public void enviarCotacoesPendentes() {
        repository.findByStatus(StatusEnum.COTACAO_PRONTA).forEach(cotacao -> {
            //s3Service.ler(BUCKET, gerarNomeDocumentoCotacao(cotacao.getId()));
            //TODO recuperar arquivo (linha de cima faz isso) e enviar por email a cotacao para o email
            // cadastrado na entidade cotacao, mover a cotacao para o status COTACAO_ENVIADA
        });
    }

    @Override
    public void gerarCotacoesPendentes() {
        //TODO recuperar entidades cotacao completas (dados pessoais e dados do veiculo) com status de
        // COTACAO_SOLICITADA, gerar a cotação e colocar no S3 usando o nome da
        // função gerarNomeDocumentoCotacaoAssinada mover a cotação para o status COTACAO_PRONTA
    }

    @Override
    public void aprovarCotacao(final UUID id) {
        var cotacao = repository.getReferenceById(id);

        cotacao.setStatus(StatusEnum.COTACAO_APROVADA);

        repository.save(cotacao);
    }

    private void enfileirarConsultas(final Cotacao cotacao) {
        sqsService.enviar(sqsDadosVeiculo, cotacao.getDadosVeiculo().getPlaca());
        sqsService.enviar(sqsDadosPessoais, cotacao.getDadosCliente().getDadosPessoais().getCpf());
    }

    private CotacaoEntity cotacaoExistenteStrategy(final CotacaoEntity cotacao) {
        switch (cotacao.getStatus()) {
            case StatusEnum.COTACAO_PRONTA,
                    StatusEnum.COTACAO_ENVIADA -> {
                return  cotacao;
            }
            default -> {
                zerarCotacao(cotacao);

                repository.save(cotacao);

                return cotacao;
            }
        }
    }

    private void zerarCotacao(final CotacaoEntity cotacao){
        final String email = cotacao.getEmail();
        final String placa = cotacao.getPlaca();
        final String cpf = cotacao.getCpf();

        cotacao.setCpf(cpf);
        cotacao.setEmail(email);
        cotacao.setPlaca(placa);

        cotacao.setStatus(StatusEnum.COTACAO_SOLICITADA);
    }

    private CotacaoEntity obterCotacaoExistente(final Cotacao cotacao) {
        return repository.findByCpfAndPlaca(
                cotacao.getDadosCliente().getDadosPessoais().getCpf(),
                cotacao.getDadosVeiculo().getPlaca());
    }

    private String gerarNomeDocumentoCotacao(final UUID idCotacao) {
        return String.format("cotacao_%s.pdf", idCotacao);
    }

    private String gerarNomeDocumentoCotacaoAssinada(final UUID idCotacao) {
        return String.format("cotacao_assinada_%s.pdf", idCotacao);
    }
}
