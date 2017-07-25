package com.studentbarter.web.application.bean;

import java.io.Serializable;

public class ItemCategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1460650618056042095L;
	
	private Long itemcid;
    private String itemcname;
    private boolean status; 
    
    public ItemCategoryBean() {
    	
    }
    
	public ItemCategoryBean(Long itemcid, String itemcname, boolean status) {
		super();
		this.itemcid = itemcid;
		this.itemcname = itemcname;
		this.status = status;
	}
	public Long getItemcid() {
		return itemcid;
	}
	public void setItemcid(Long itemcid) {
		this.itemcid = itemcid;
	}
	public String getItemcname() {
		return itemcname;
	}
	public void setItemcname(String itemcname) {
		this.itemcname = itemcname;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
