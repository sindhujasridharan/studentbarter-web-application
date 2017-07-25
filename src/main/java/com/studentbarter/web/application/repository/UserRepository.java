package com.studentbarter.web.application.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.studentbarter.web.application.model.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(Account account);
    
    @Modifying
    @Query("update User u set u.firstName = ?1, u.middleName =?2, u.lastName=?3, u.dateOfBirth=?4, u.phoneNumber=?5, u.address=?6 where u.userId = ?7")
    void updateUserInfo(String firstName, String middleName, String lastName, Date dateOfBirth, String phoneNumber, String address, Long userId);
    
    @Modifying
    @Query("update User u set u.roles = ?1 where u.userId = ?7")
    void updateRole(Set<Role> roles, Long userId);
}
