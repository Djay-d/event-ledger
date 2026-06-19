
package com.eventledger.event_gateway.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Event {

    @Id
    private String eventId;

    private String accountId;

    private String type;

    private Double amount;

    private String currency;

    private Instant eventTimestamp;
}