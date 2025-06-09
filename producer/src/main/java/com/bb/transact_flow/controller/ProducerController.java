package com.bb.transact_flow.controller;

import com.bb.transact_flow.dto.BuyEventRequest;
import com.bb.transact_flow.usecase.PublishBuyEventUseCase;
import jakarta.validation.Valid;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final PublishBuyEventUseCase publishBuyEventUseCase;

    public ProducerController(PublishBuyEventUseCase publishBuyEventUseCase) {
        this.publishBuyEventUseCase = publishBuyEventUseCase;
    }

    @PostMapping("/publish")
    public void post(@RequestBody @Valid BuyEventRequest request) {
        publishBuyEventUseCase.execute(request);
    }
}