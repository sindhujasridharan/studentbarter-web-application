package com.db.studentbarter.model;

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
@Table(name = "items")
public class Post {
	
	private Long itemid;
	private String title;	
	private Long statusid;
	private String description;
	private Double itemworth;
	private Long itemcid;
	private Long enduserid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date addeddate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date solddate;	
	private Long addedby;
	private String rowstate;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public Long getEnduserid() {
		return enduserid;
	}
	public void setEnduserid(Long enduserid) {
		this.enduserid = enduserid;
	}
	
	public Date getSolddate() {
		return solddate;
	}
	public void setSolddate(Date solddate) {
		this.solddate = solddate;
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
		
	public String getRowstate() {
		return rowstate;
	}
	public void setRowstate(String rowstate) {
		this.rowstate = rowstate;
	}
	

}