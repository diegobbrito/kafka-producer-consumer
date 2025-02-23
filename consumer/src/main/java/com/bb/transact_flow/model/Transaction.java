package com.bb.transact_flow.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime timestamp;

}
