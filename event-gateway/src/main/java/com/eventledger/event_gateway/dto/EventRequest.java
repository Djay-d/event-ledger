package main.java.com.eventledger.event_gateway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Instant;

@Data
public class EventRequest {

    @NotBlank
    private String eventId;

    @NotBlank
    private String accountId;

    @NotBlank
    private String type;

    @Positive
    private Double amount;

    @NotBlank
    private String currency;

    private Instant eventTimestamp;
}