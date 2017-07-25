package com.db.studentbarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.studentbarter.model.Role;

public interface UserRoleRepository extends JpaRepository<Role, Long>{
	static Long DEFAULT_USER_ROLE = 1L;
	static Long ADMIN_USER_ROLE = 2L;
	static Long MODERATOR_USER_ROLE = 3L;
	
}
