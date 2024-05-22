package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.exceptions.BusinessException;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.repository.ApoliceRepository;
import com.fiap.pos.tech.challenge.repository.CotacaoRepository;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import com.fiap.pos.tech.challenge.service.S3Service;
import com.fiap.pos.tech.challenge.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.pos.tech.challenge.enums.StatusEnum.APOLICE_ENCERRADA;
import static com.fiap.pos.tech.challenge.enums.StatusEnum.DESTRATO_ENVIADO;

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

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Value("${sqs.queueName.dados-veiculo}")
    private String sqsDadosVeiculo;

    @Value("${sqs.queueName.dados-pessoais}")
    private String sqsDadosPessoais;

    private static final String BUCKET = "documentos";

    @Override
    public void receberCotacao(final Cotacao cotacao) {
        if (validarCotacaoExistente(cotacao)) {
            repository.save(
                    mapper.toDb(
                            cotacao,
                            cotacao.getDadosCadastrais().getDadosPessoais(),
                            cotacao.getDadosCadastrais().getDadosContato(),
                            cotacao.getDadosCadastrais().getDadosEndereco(),
                            cotacao.getDadosVeiculo()
                    )
            );

            enfileirarConsultas(cotacao);
        }
    }

    @Override
    public Cotacao consultarCotacao(final UUID id) {
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
    public void enviarCotacao(final UUID id) {
        var cotacao = repository.getReferenceById(id);

        //TODO enviar cotação existente por email
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

    @Override
    public void enviarCotacao(final UUID id, final String email) {
        var cotacao = repository.getReferenceById(id);

        if (cotacao.getStatus().equals(StatusEnum.COTACAO_PRONTA) ||
                cotacao.getStatus().equals(StatusEnum.COTACAO_ENVIADA)) {
            if (email == null) {
                //TODO enviar cotação para o email da cotação
            } else {
                //TODO enviar cotação para o email informado
            }
        } else {
            throw new BusinessException("Cotação em preparação, em breve você receberá por email");
        }
    }

    @Override
    public void deletarCotacao(final UUID id) {
        final var cotacao = repository.getReferenceById(id);

        if (!cotacao.getStatus().equals(StatusEnum.COTACAO_APROVADA)) {
            repository.delete(cotacao);
        } else {
            throw new BusinessException("Apenas cotações não aprovadas podem ser excluidas");
        }
    }

    private Boolean validarCotacaoExistente(final Cotacao cotacao) {
        CotacaoEntity cotacaoExistente = obterCotacaoExistente(cotacao);

        if (cotacaoExistente != null) {
            switch (cotacaoExistente.getStatus()) {
                case COTACAO_APROVADA -> {
                    var apolice = apoliceRepository.getReferenceById(cotacao.getId());
                    if (apolice.getStatus().equals(APOLICE_ENCERRADA) ||
                            apolice.getStatus().equals(DESTRATO_ENVIADO)) {
                        zerarCotacao(cotacaoExistente);

                        repository.save(cotacaoExistente);
                    } else {
                        throw new BusinessException(String.format("Apólice %s está ativa para esse " +
                                "CPF e Placa", apolice.getId()));
                    }
                }
                case COTACAO_RECUSADA -> {
                    zerarCotacao(cotacaoExistente);

                    repository.save(cotacaoExistente);
                }
                case COTACAO_SOLICITADA, COTACAO_PRONTA ->
                    throw new BusinessException("Cotação em preparação, logo o(a) senhor(a) " +
                            "irá recebe-la por email.");
                default ->
                    enviarCotacao(cotacaoExistente.getId());
            }
        }

        return  cotacaoExistente == null;
    }

    private void enfileirarConsultas(final Cotacao cotacao) {
        sqsService.enviar(sqsDadosVeiculo, cotacao.getDadosVeiculo().getPlaca());
        sqsService.enviar(sqsDadosPessoais, cotacao.getDadosCadastrais().getDadosPessoais().getCpf());
    }

    private void zerarCotacao(final CotacaoEntity cotacao){
        final String email = cotacao.getEmail();
        final String placa = cotacao.getPlaca();
        final String cpf = cotacao.getCpf();

        cotacao.setCpf(cpf);
        cotacao.setEmail(email);
        cotacao.setPlaca(placa);
        cotacao.setDataHoraCriacao(LocalDateTime.now());
        cotacao.setDataHoraExpiracao(LocalDateTime.now().plusDays(7));
        cotacao.setStatus(StatusEnum.COTACAO_SOLICITADA);

        cotacao.setNome(null);
        cotacao.setSobrenome(null);
        cotacao.setNomeMae(null);
        cotacao.setNomePai(null);
        cotacao.setDddFixo(null);
        cotacao.setFixo(null);
        cotacao.setDddCelular(null);
        cotacao.setCelular(null);
        cotacao.setDddRecado(null);
        cotacao.setRecado(null);
        cotacao.setLogradouro(null);
        cotacao.setNumero(null);
        cotacao.setComplemento(null);
        cotacao.setBairro(null);
        cotacao.setCidade(null);
        cotacao.setEstado(null);
        cotacao.setCep(null);
        cotacao.setPlaca(null);
        cotacao.setRenavan(null);
        cotacao.setChassi(null);
        cotacao.setModelo(null);
        cotacao.setAnoFabricacao(null);
        cotacao.setAnoModelo(null);
    }

    private CotacaoEntity obterCotacaoExistente(final Cotacao cotacao) {
        return repository.findByCpfAndPlaca(
                cotacao.getDadosCadastrais().getDadosPessoais().getCpf(),
                cotacao.getDadosVeiculo().getPlaca());
    }

    private String gerarNomeDocumentoCotacao(final UUID idCotacao) {
        return String.format("cotacao_%s.pdf", idCotacao);
    }

    private String gerarNomeDocumentoCotacaoAssinada(final UUID idCotacao) {
        return String.format("cotacao_assinada_%s.pdf", idCotacao);
    }
}
