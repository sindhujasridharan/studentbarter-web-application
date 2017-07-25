package com.db.studentbarter.service;

import java.util.Set;

import com.db.studentbarter.model.ItemCategory;;

public interface ItemCategoryService {
	
	Set<ItemCategory> findItemCategories();
	
    void save(ItemCategory itemcategory);
    
    ItemCategory findItemCategoryById(Long itemCategoryId);


}
