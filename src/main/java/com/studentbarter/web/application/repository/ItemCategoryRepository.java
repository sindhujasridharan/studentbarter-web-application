package com.studentbarter.web.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentbarter.web.application.model.*;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    
}
