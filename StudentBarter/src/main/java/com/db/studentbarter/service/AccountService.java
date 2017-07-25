package com.db.studentbarter.service;

import com.db.studentbarter.model.Account;


public interface AccountService {
    void save(Account account);

    Account findByUsername(String username);
}
