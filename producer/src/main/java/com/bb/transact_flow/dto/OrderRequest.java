package com.bb.transact_flow.dto;

import com.bb.transact_flow.enumerator.PaymentStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(
        @NotNull UUID orderId,
        @NotNull UUID customerId,
        @NotNull @PositiveOrZero BigDecimal amount,
        @NotNull PaymentStatusEnum status,
        @NotNull String date
) {}
