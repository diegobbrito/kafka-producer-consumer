package com.bb.transact_flow.dto;

import com.bb.transact_flow.enumerator.PaymentStatusEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(UUID orderId, UUID customerId, BigDecimal amount, PaymentStatusEnum status, String date) {
}
