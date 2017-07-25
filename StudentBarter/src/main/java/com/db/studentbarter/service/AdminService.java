package com.db.studentbarter.service;

import java.util.List;

import com.db.studentbarter.model.WebsiteFeedback;

public interface AdminService {
		
    public List<WebsiteFeedback> findAllFeedbacks();
            
    public void insertWebsiteFeedback(WebsiteFeedback websiteFeedback);
    
}
