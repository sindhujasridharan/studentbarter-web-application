package com.studentbarter.web.application.service;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    

    @Override
    public void create(User user) {
    	List<Role> roles = new ArrayList<Role>();
    	roles.add(roleRepository.findOne(RoleRepository.DEFAULT_USER_ROLE));
        user.setRoles(new HashSet<>(roles));
        user.setAccount(accountRepository.findOne(user.getAccount().getAccountId()));
        userRepository.save(user);
    }
    
    @Override
    public void update(User user) {
    	System.out.println(user.getFirstName());
    	System.out.println(user.getUserId());
    	
        userRepository.updateUserInfo(user.getFirstName(), user.getMiddleName(), 
        		user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber(), 
        		user.getAddress(), user.getUserId());
    }

	@Override
	public User findByAccount(Account account) {
		return userRepository.findByAccount(account);
	}	
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public List<User> findUsers() {
		return userRepository.findAll();
	}
}
