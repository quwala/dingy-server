package com.dingy.dingyserver.datamodels;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="DINGY_USERS")
public class DingyUser {
	
	@Id
	private String id;//this is the unique identifier of every user, their Google Account ID.
	
	private Date date_created;
	
//	private List<Group> groups;
	
	public DingyUser(){
		this.id = "hamorabi";
		this.date_created = new Date();
	}
	
	public DingyUser(String id)
	{
		this.id = id;
		
		this.date_created = new Date();//this sets the date at the current date and time (not an empty date object)
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreated() {
		return date_created;
	}
	public void setCreated(Date created) {
		this.date_created = created;
	}
	
	
	
}
