package com.studentbarter.web.application.bean;

import java.io.Serializable;

public class WebsiteFeedbackBean implements Serializable {	
	
	private static final long serialVersionUID = 1838031115533374672L;
	
	private String fullName;
	private String userEmail;
	private String feedbackMessage;	    
    private String givenOn;	
	
	public WebsiteFeedbackBean(String fullName, String userEmail, String feedbackMessage, String givenOn) {
		super();
		this.fullName = fullName;
		this.userEmail = userEmail;
		this.feedbackMessage = feedbackMessage;
		this.givenOn = givenOn;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFeedbackMessage() {
		return feedbackMessage;
	}
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}
	public String getGivenOn() {
		return givenOn;
	}
	public void setGivenOn(String givenOn) {
		this.givenOn = givenOn;
	}	

}