package com.db.studentbarter.service;

import java.util.List;

import com.db.studentbarter.model.Account;
import com.db.studentbarter.model.User;


public interface UserService {
    void create(User user);
    
    void update(User user);

    User findByAccount(Account account);
    
    User findByUserId(Long userId);
    
    List<User> findUsers();
    
}
