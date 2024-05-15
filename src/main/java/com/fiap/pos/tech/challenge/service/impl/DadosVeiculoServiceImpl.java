package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.service.DadosVeiculoService;
import org.springframework.stereotype.Service;

@Service
public class DadosVeiculoServiceImpl implements DadosVeiculoService {
    @Override
    public void consultar(final String cpf) {
        //TODO Criar uma planilha com dados fake para serem utilizados na consulta de dados para cotação
        //TODO Criar bucket no S3 para hospedar essa planilha
        //TODO Consultar essa planilha que está no S3 com os dados do veiculo
    }
}
