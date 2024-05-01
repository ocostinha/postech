package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.entities.Cotacao;
import com.fiap.pos.tech.challenge.mappers.CotacaoMapper;
import com.fiap.pos.tech.challenge.repository.CotacaoRepository;
import com.fiap.pos.tech.challenge.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotacaoServiceImpl implements CotacaoService {
    @Autowired
    private CotacaoRepository repository;

    @Autowired
    private CotacaoMapper mapper;

    @Override
    public Cotacao receberCotacao(Cotacao cotacao) {
        // TODO validar se existe cotação com o mesmo cpf / placa
        // TODO enviar para a fila de comunicação com o fornecedor de dados do cliente
        // TODO enviar para a fila de comunicação com o fornecedor de dados do veiculo

        return mapper.toEntity(repository.save(mapper.toDb(cotacao)));
    }
}
