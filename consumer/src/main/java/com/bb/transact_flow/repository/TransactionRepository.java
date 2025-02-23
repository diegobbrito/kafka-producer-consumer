package com.bb.transact_flow.repository;

import com.bb.transact_flow.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
