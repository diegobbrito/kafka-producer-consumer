package com.bb.transact_flow.controller;

import com.bb.transact_flow.dto.BuyEventRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private static final String TOPIC = "purchase-orders";
    private final KafkaTemplate<String, BuyEventRequest> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, BuyEventRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public void post(@RequestBody BuyEventRequest request) {
        kafkaTemplate.send(TOPIC, request);
    }
}