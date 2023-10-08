package com.easylearnz.gateway.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.easylearnz.gateway.model.TransactionMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;

    public void send(String topicName, UUID Key, TransactionMessage message) {
        CompletableFuture<SendResult<UUID, TransactionMessage>> future = kafkaTemplate.send(topicName, Key, message);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
                log.error(exception.getMessage());
            } else {
                future.complete(sendResult);
            }

            log.info("Transaction status to kafka topic: " + message.getStatus());
        });
    }

}
