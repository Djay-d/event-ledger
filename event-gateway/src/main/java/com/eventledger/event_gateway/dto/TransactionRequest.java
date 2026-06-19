package main.java.com.eventledger.event_gateway.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TransactionRequest {

    private String eventId;
    private String type;
    private Double amount;
    private Instant timestamp;
}