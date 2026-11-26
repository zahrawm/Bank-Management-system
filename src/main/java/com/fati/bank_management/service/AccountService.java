package com.fati.bank_management.service;

import com.fati.bank_management.dto.AccountResponse;
import com.fati.bank_management.dto.CreateAccountRequest;
import com.fati.bank_management.entity.Account;
import com.fati.bank_management.entity.User;
import com.fati.bank_management.exception.AccountNotFoundException;
import com.fati.bank_management.exception.UserNotFoundException;
import com.fati.bank_management.repository.AccountRepository;
import com.fati.bank_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        User owner = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));

        Account account = new Account();
        account.setUser(owner);
        account.setAccountName(request.accountName().trim());
        account.setAccountType(request.accountType());
        account.setAccountNumber(generateAccountNumber());

        Account saved = accountRepository.save(account);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AccountResponse getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return toResponse(account);
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> getAccountsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return accountRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    private String generateAccountNumber() {
        String number;
        do {
            number = String.format("%010d", random.nextLong(10_000_000_000L));
        } while (accountRepository.existsByAccountNumber(number));
        return number;
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance(),
                account.getUser().getId(),
                account.getCreatedAt()
        );
    }
}