package com.studentbarter.web.application.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.repository.*;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
   
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;    
    
	@Override
    public Set<ItemCategory> findItemCategories() {
        Set<ItemCategory> itemCategorySets = new TreeSet<ItemCategory>(itemCategoryRepository.findAll());       
        return itemCategorySets;
    }

	@Override
	public void save(ItemCategory itemcategory) {			
		itemCategoryRepository.save(itemcategory);
	}	
	
	@Override
	public ItemCategory findItemCategoryById(Long itemCategoryId) {
		return itemCategoryRepository.findOne(itemCategoryId);
	}
	
}
