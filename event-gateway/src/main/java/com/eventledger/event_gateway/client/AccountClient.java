package com.eventledger.event_gateway.client;

import com.eventledger.event_gateway.config.FeignConfig;
import com.eventledger.event_gateway.dto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface AccountClient {

    @PostMapping("/accounts/{accountId}/transactions")
    Object applyTransaction(
            @PathVariable String accountId,
            @RequestBody TransactionRequest request);
}