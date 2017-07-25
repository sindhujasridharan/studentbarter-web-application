package com.db.studentbarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.studentbarter.model.WebsiteFeedback;

public interface WebsiteFeedbackRepository extends JpaRepository<WebsiteFeedback, Long> {

}
