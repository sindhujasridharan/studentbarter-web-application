package com.studentbarter.web.application.service;

import com.studentbarter.web.application.model.*;

public interface RatingService {
	ItemRating findItemRatingByItemId(Long itemid);

	void insertRatedItem(ItemRating itemRating);
}
