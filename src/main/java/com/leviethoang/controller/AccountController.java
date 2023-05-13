package com.leviethoang.controller;

import com.leviethoang.model.Account;
import com.leviethoang.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceImpl service;

    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody @Valid Account account){
        return ResponseEntity.ok(service.createUser(account));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<Account> createAdmin(@RequestBody @Valid Account account){
        return ResponseEntity.ok(service.createAdmin(account));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Account>> listAllAccounts(){
        return ResponseEntity.ok(service.listAllAccounts());
    }


}
