package com.studentbarter.web.application.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "websitefeedback")
public class WebsiteFeedback {
	
	private Long feedbackid;
	private String description;
	private Long addedby;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date addeddate;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(Long feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getAddedby() {
		return addedby;
	}
	public void setAddedby(Long addedby) {
		this.addedby = addedby;
	}
	public Date getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}
	

}
