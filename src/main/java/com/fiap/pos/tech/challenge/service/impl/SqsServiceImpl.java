package com.fiap.pos.tech.challenge.service.impl;

import com.fiap.pos.tech.challenge.service.SqsService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SqsServiceImpl implements SqsService {
    @Autowired
    SqsTemplate sqsTemplate;

    @Override
    public void send(String queueName, String message) {
        this.sqsTemplate.send(queueName, MessageBuilder.withPayload(message).build());
    }

    @SqsListener(value = "${sqs.queueName.dados-pessoais}")
    public void dadosPessoaisListener(String message) {
        System.out.println(message);
    }

    @SqsListener(value = "${sqs.queueName.dados-pessoais}")
    public void dadosVeiculoListener(String message) {
        System.out.println(message);
    }
}
