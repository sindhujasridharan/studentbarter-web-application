package com.db.studentbarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.studentbarter.model.ItemCategory;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    
}
