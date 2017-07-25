package com.studentbarter.web.application.service;

import java.util.List;

import com.studentbarter.web.application.model.*;


public interface UserService {
    void create(User user);
    
    void update(User user);

    User findByAccount(Account account);
    
    User findByUserId(Long userId);
    
    List<User> findUsers();
    
}
