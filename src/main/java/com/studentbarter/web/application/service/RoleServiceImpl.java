package com.studentbarter.web.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.repository.*;
@Service
public class RoleServiceImpl implements RoleService {
   
    @Autowired
    private RoleRepository roleRepository;       

    @Override
    public void save(Role role) {    
        roleRepository.save(role);
    }	
		
}
