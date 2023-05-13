package com.leviethoang.service;

import com.leviethoang.model.Account;

import java.util.List;

public interface AccountService {
    public Account createUser(Account account);
    public Account createAdmin(Account account);
    public Account findByUsername(String username);
    public List<Account> listAllAccounts();
}
