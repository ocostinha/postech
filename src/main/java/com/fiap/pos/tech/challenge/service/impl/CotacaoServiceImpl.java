package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.repository.CotacaoRepository;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import com.fiap.pos.tech.challenge.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CotacaoServiceImpl implements CotacaoService {
    @Autowired
    private CotacaoRepository repository;

    @Autowired
    private CotacaoMapper mapper;

    @Autowired
    private SqsService sqsService;

    @Value("${sqs.queueName.dados-veiculo}")
    private String sqsDadosVeiculo;

    @Value("${sqs.queueName.dados-pessoais}")
    private String sqsDadosPessoais;

    @Override
    public Cotacao receberCotacao(Cotacao cotacao) {
        CotacaoEntity cotacaoExistente = obterCotacaoExistente(cotacao);

        if (cotacaoExistente != null) {
            return mapper.toEntity(cotacaoExistenteStrategy(cotacaoExistente));
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

        sqsService.enviar(sqsDadosVeiculo, cotacao.getDadosVeiculo().getPlaca());
        sqsService.enviar(sqsDadosPessoais, cotacao.getDadosCliente().getDadosPessoais().getCpf());

        return mapper.toEntity(
                novaCotacao
        );
    }

    private CotacaoEntity cotacaoExistenteStrategy(CotacaoEntity cotacao) {
        switch (cotacao.getStatus()) {
            case StatusEnum.SOLICITADO -> {
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
        String email = cotacao.getEmail();
        String placa = cotacao.getPlaca();
        String cpf = cotacao.getCpf();

        cotacao.setCpf(cpf);
        cotacao.setEmail(email);
        cotacao.setPlaca(placa);

        cotacao.setStatus(StatusEnum.SOLICITADO);
    }

    private CotacaoEntity obterCotacaoExistente(Cotacao cotacao) {
        return repository.findByCpfAndPlaca(
                cotacao.getDadosCliente().getDadosPessoais().getCpf(),
                cotacao.getDadosVeiculo().getPlaca());
    }
}
