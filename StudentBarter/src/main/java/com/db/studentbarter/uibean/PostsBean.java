package com.db.studentbarter.uibean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PostsBean implements Serializable {
			
	private static final long serialVersionUID = -2452406391170837091L;
	
	private Long itemid;
	private String title;	
	private Long statusid;
	private String description;
	private Double itemworth;
	private Long itemcid;	
	private String itemCategoryName;
	private Long enduserid;	
	private String endUserName;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date solddate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date addeddate;
	private Long addedby;
	private String addedbyName;
	private String rowstate;
	
	public PostsBean(Long itemid, String title, Long statusid, String description, Double itemworth,
			Long itemcid, String itemCategoryName, Long enduserid, String endUserName,
			Date solddate, Date addeddate, Long addedby, String addedbyName, String rowstate) {
		super();
		this.itemid = itemid;
		this.title = title;
		this.statusid = statusid;
		this.description = description;
		this.itemworth = itemworth;
		this.itemcid = itemcid;
		this.itemCategoryName = itemCategoryName;
		this.enduserid = enduserid;
		this.endUserName = endUserName;
		this.solddate = solddate;
		this.addeddate = addeddate;
		this.addedby = addedby;
		this.addedbyName = addedbyName;
		this.rowstate = rowstate;
	}
	
	public PostsBean() {
		
	}

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatusid() {
		return statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getItemworth() {
		return itemworth;
	}

	public void setItemworth(Double itemworth) {
		this.itemworth = itemworth;
	}

	public Long getItemcid() {
		return itemcid;
	}

	public void setItemcid(Long itemcid) {
		this.itemcid = itemcid;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public Long getEnduserid() {
		return enduserid;
	}

	public void setEnduserid(Long enduserid) {
		this.enduserid = enduserid;
	}

	public String getEndUserName() {
		return endUserName;
	}

	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}

	public Date getAddeddate() {
		return addeddate;
	}

	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}

	public Long getAddedby() {
		return addedby;
	}

	public void setAddedby(Long addedby) {
		this.addedby = addedby;
	}

	public String getAddedbyName() {
		return addedbyName;
	}

	public void setAddedbyName(String addedbyName) {
		this.addedbyName = addedbyName;
	}

	public String getRowstate() {
		return rowstate;
	}

	public void setRowstate(String rowstate) {
		this.rowstate = rowstate;
	}	

	public Date getSolddate() {
		return solddate;
	}

	public void setSolddate(Date solddate) {
		this.solddate = solddate;
	}
	
	public String getFormattedSoldDate() {		
		return new SimpleDateFormat("yyyy-MM-dd").format(this.solddate);
	}
	
	public String getFormattedAddedDate() {		
		return new SimpleDateFormat("yyyy-MM-dd").format(this.addeddate);
	}
	

}
