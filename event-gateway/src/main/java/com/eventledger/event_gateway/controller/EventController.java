package main.java.com.eventledger.event_gateway.controller;

import com.eventledger.event_gateway.dto.EventRequest;
import com.eventledger.event_gateway.entity.Event;
import com.eventledger.event_gateway.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Event createEvent(
            @Valid @RequestBody EventRequest request) {

        return eventService.createEvent(request);
    }

    @GetMapping("/{id}")
    public Event getEvent(
            @PathVariable String id) {

        return eventService.getEvent(id);
    }

    @GetMapping
    public List<Event> getEvents(
            @RequestParam String account) {

        return eventService.getEventsByAccount(account);
    }

    @GetMapping("/health")
    public String health() {

        return "UP";
    }
}