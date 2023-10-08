package com.easylearnz.gateway.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easylearnz.gateway.model.TransactionMessage;
import com.easylearnz.gateway.service.KafkaProducerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EventController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("event")
    ResponseEntity<String> event(@RequestBody TransactionMessage message) {
        UUID uuid = UUID.randomUUID();
        log.info("We received the transaction with the key " + uuid);
        kafkaProducerService.send("transaction-topic", uuid, message);

        return ResponseEntity.ok("Sent");

    }
}
