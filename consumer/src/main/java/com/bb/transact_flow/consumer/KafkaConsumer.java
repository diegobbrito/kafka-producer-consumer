package com.bb.transact_flow.consumer;

import com.bb.transact_flow.model.Transaction;
import com.bb.transact_flow.repository.TransactionRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    private final TransactionRepository transactionRepository;

    private static final Logger logger = Logger.getLogger(KafkaConsumer.class.getName());

    public KafkaConsumer(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @KafkaListener(topics = "purchase-orders", groupId = "transact-flow-group")
    public void consume(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Transaction transaction = objectMapper.readValue(message, Transaction.class);

            if (transaction.getAmount().signum() < 0 || transaction.getStatus() == null || transaction.getStatus().isEmpty()) {
                logger.severe("Invalid transaction data: " + message);
                return;
            }

            transaction.setTimestamp(LocalDateTime.now());
            transactionRepository.save(transaction);
            logger.info("Transaction saved: " + transaction);
        } catch (Exception e) {
            logger.severe("Error processing transaction: " + e.getMessage());
        }
    }
}