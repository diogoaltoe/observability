package com.example.controller;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountInfo(@PathVariable("id") Long id) {
        try {
            log.info("Requested account for id: {}", id);
            Optional<Account> accountOptional = accountRepository.findById(id);
            if(accountOptional.isEmpty()) {
                log.warn("Not found account for id: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Account account = accountOptional.get();
            log.info("Found account info " + account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Failed to find account for id: {}", id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}