package com.studentbarter.web.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentbarter.web.application.model.*;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
