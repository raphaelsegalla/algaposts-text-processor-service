package com.algaworks.algaposts.text.processor.service.infraestructure.rabbitmq;

import com.algaworks.algaposts.text.processor.service.domain.model.TextProcessorData;
import com.algaworks.algaposts.text.processor.service.domain.service.TextProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.algaworks.algaposts.text.processor.service.infraestructure.rabbitmq.RabbitMQConfig.QUEUE_TEXT_PROCESSOR;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TextProcessorService textProcessorService;

    @RabbitListener(queues = QUEUE_TEXT_PROCESSOR)
    @SneakyThrows
    public void handleProcessingText(@Payload TextProcessorData textProcessorData, @Headers Map<String, Object> headers) {
        log.info("Text processing: postId {} body {}", textProcessorData.getPostId(), textProcessorData.getPostBody());
        log.info("Headers: {}", headers);

        textProcessorService.textProcessorCalculator(textProcessorData);

//        Thread.sleep(Duration.ofSeconds(5).toMillis()); // Simulating a delay for processing
    }

}
