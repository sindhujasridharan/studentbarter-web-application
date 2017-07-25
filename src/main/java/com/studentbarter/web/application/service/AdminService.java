package com.studentbarter.web.application.service;

import java.util.List;

import com.studentbarter.web.application.model.*;

public interface AdminService {
		
    public List<WebsiteFeedback> findAllFeedbacks();
            
    public void insertWebsiteFeedback(WebsiteFeedback websiteFeedback);
    
}
