package com.db.studentbarter.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.studentbarter.model.ItemCategory;
import com.db.studentbarter.repository.ItemCategoryRepository;

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
