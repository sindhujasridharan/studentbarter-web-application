package com.db.studentbarter.service;

import com.db.studentbarter.model.ItemRating;

public interface RatingService {
	ItemRating findItemRatingByItemId(Long itemid);

	void insertRatedItem(ItemRating itemRating);
}
