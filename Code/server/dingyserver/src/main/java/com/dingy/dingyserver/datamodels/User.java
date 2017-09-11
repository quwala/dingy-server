package com.dingy.dingyserver.datamodels;

import java.util.Date;

import javax.persistence.Entity;



@Entity
public class User {
	
	private String id;//this is the unique identifier of every user, their Google Account ID.
	private Date created;
//	private List<Group> groups;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
}
