package com.db.studentbarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.studentbarter.model.ItemRating;
import com.db.studentbarter.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    
    @Override
    public ItemRating findItemRatingByItemId(Long itemid){
    	return ratingRepository.findItemRatingByItemId(itemid);
    }
    
    @Override
    public void insertRatedItem(ItemRating itemRating) {
    	ratingRepository.save(itemRating);
    }
    
 
}
