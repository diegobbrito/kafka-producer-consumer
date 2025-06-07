package com.bb.transact_flow.usecase;

import com.bb.transact_flow.dto.BuyEventRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.Instant;

@Service
public class PublishBuyEventUseCase {

    private static final String TOPIC = "purchase-orders";
    private final KafkaTemplate<String, BuyEventRequest> kafkaTemplate;

    public PublishBuyEventUseCase(KafkaTemplate<String, BuyEventRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void execute(BuyEventRequest request) {
        validate(request);
        kafkaTemplate.send(TOPIC, request);
    }

    private void validate(BuyEventRequest request) {
        if(request.data().order().amount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            throw new IllegalArgumentException("Amount exceeds the limit of 1000");
        }
        Instant timestamp = Instant.parse(request.timestamp());
        if (timestamp.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Timestamp must not be in the future");
        }
    }
}