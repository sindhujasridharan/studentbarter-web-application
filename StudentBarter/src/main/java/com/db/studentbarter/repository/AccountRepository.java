package com.db.studentbarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.studentbarter.model.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
