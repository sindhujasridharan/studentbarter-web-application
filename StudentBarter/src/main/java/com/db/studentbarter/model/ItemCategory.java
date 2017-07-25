package com.db.studentbarter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemcategories")
public class ItemCategory implements Comparable<ItemCategory>{
	private Long itemcid;
    private String itemcname;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Override
    public int compareTo(ItemCategory itemCategory) {
        return this.itemcname.compareTo(itemCategory.getItemcname());
    }
	

}
