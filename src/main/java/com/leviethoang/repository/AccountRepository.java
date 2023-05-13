package com.leviethoang.repository;

import com.leviethoang.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , Long> {
    public Account findByUsername(String username);
}
