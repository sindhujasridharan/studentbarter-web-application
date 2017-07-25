package com.db.studentbarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.studentbarter.model.WebsiteFeedback;
import com.db.studentbarter.repository.WebsiteFeedbackRepository;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private WebsiteFeedbackRepository websiteFeedbackRepository;
    
    @Override
    public List<WebsiteFeedback> findAllFeedbacks() {
    	return websiteFeedbackRepository.findAll();
    }
    
    @Override
    public void insertWebsiteFeedback(WebsiteFeedback websiteFeedback) {
    	websiteFeedbackRepository.save(websiteFeedback);
    }

}
