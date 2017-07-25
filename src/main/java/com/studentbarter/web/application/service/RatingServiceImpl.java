package com.studentbarter.web.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.repository.*;

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
