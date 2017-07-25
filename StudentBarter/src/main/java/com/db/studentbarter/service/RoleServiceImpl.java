package com.db.studentbarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.studentbarter.model.Role;
import com.db.studentbarter.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
   
    @Autowired
    private RoleRepository roleRepository;       

    @Override
    public void save(Role role) {    
        roleRepository.save(role);
    }	
		
}
