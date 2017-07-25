package com.studentbarter.web.application.service;

import com.studentbarter.web.application.model.*;


public interface AccountService {
    void save(Account account);

    Account findByUsername(String username);
}
