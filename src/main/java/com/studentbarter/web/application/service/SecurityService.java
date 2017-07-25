package com.studentbarter.web.application.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
