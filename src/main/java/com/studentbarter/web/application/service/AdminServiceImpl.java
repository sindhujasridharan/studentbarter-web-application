package com.studentbarter.web.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.repository.*;

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
