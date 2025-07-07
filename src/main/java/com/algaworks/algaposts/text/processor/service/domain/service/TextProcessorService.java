package com.algaworks.algaposts.text.processor.service.domain.service;

import com.algaworks.algaposts.text.processor.service.domain.model.TextProcessorData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TextProcessorService {

    private final RabbitTemplate rabbitTemplate;

    public void textProcessorCalculator(TextProcessorData textProcessorData) {
        String text = textProcessorData.getPostBody();

        // Remove espaços extras e divide o texto em palavras
        String[] palavras = text.trim().split("\\s+");

        int quantidadePalavras = palavras.length;
        long quebraDeLinhas = text.lines().count();

        log.info("Quantidade de palavras e quebra de linhas: {} - {}", quantidadePalavras, quebraDeLinhas);


    }
}
