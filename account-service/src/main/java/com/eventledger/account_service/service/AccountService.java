package com.eventledger.account_service.service;

import com.eventledger.account_service.dto.TransactionRequest;
import com.eventledger.account_service.entity.Account;
import com.eventledger.account_service.entity.Transaction;
import com.eventledger.account_service.repository.AccountRepository;
import com.eventledger.account_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account applyTransaction(String accountId,
            TransactionRequest request) {

        Account account = accountRepository
                .findById(accountId)
                .orElse(new Account(accountId, 0.0));

        if ("CREDIT".equalsIgnoreCase(request.getType())) {
            account.setBalance(account.getBalance() + request.getAmount());
        } else if ("DEBIT".equalsIgnoreCase(request.getType())) {
            account.setBalance(account.getBalance() - request.getAmount());
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setEventId(request.getEventId());
        transaction.setAccountId(accountId);
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(request.getTimestamp());

        transactionRepository.save(transaction);

        return account;
    }

    public Double getBalance(String accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElse(0.0);
    }

    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId)
                .orElse(new Account(accountId, 0.0));
    }
}