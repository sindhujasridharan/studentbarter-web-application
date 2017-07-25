package com.studentbarter.web.application.service;

import java.util.Set;

import com.studentbarter.web.application.model.*;

public interface ItemCategoryService {
	
	Set<ItemCategory> findItemCategories();
	
    void save(ItemCategory itemcategory);
    
    ItemCategory findItemCategoryById(Long itemCategoryId);


}
