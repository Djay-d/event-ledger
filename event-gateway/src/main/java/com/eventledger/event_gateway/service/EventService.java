package main.java.com.eventledger.event_gateway.service;

import com.eventledger.event_gateway.client.AccountClient;
import com.eventledger.event_gateway.dto.EventRequest;
import com.eventledger.event_gateway.dto.TransactionRequest;
import com.eventledger.event_gateway.entity.Event;
import com.eventledger.event_gateway.repository.EventRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AccountClient accountClient;

    @Retry(name = "accountService")
    public Event createEvent(EventRequest request) {

        Event existing = eventRepository
                .findById(request.getEventId())
                .orElse(null);

        if (existing != null) {
            return existing;
        }

        Event event = new Event();

        event.setEventId(request.getEventId());
        event.setAccountId(request.getAccountId());
        event.setType(request.getType());
        event.setAmount(request.getAmount());
        event.setCurrency(request.getCurrency());
        event.setEventTimestamp(request.getEventTimestamp());

        eventRepository.save(event);

        TransactionRequest transaction = new TransactionRequest();

        transaction.setEventId(request.getEventId());
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(
                request.getEventTimestamp());

        accountClient.applyTransaction(
                request.getAccountId(),
                transaction);

        return event;
    }

    public Event getEvent(String id) {

        return eventRepository
                .findById(id)
                .orElse(null);
    }

    public List<Event> getEventsByAccount(
            String accountId) {

        return eventRepository
                .findByAccountIdOrderByEventTimestampAsc(
                        accountId);
    }
}