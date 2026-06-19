package com.eventledger.account_service.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class TransactionRequest {

    private String eventId;
    private String type;
    private Double amount;
    private Instant timestamp;
}
