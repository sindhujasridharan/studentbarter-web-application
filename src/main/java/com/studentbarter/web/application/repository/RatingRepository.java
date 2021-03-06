package com.studentbarter.web.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentbarter.web.application.model.*;

public interface RatingRepository extends JpaRepository<ItemRating, Long> {	
	
	@Query("select p from ItemRating p where p.itemid = ?1")
	ItemRating findItemRatingByItemId(Long itemid);

}
