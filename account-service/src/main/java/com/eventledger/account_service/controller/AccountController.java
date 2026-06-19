package com.eventledger.account_service.controller;

import com.eventledger.account_service.dto.TransactionRequest;
import com.eventledger.account_service.entity.Account;
import com.eventledger.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{accountId}/transactions")
    public Account applyTransaction(
            @PathVariable String accountId,
            @RequestBody TransactionRequest request) {

        return accountService.applyTransaction(accountId, request);
    }

    @GetMapping("/{accountId}/balance")
    public Double getBalance(@PathVariable String accountId) {

        return accountService.getBalance(accountId);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable String accountId) {

        return accountService.getAccount(accountId);
    }

    @GetMapping("/health")
    public String health() {

        return "UP";
    }
}