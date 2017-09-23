package com.dingy.dingyserver.datamodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DingyRating {
	
	@Id
	private String userId;
	//@Column (name = "user")
	private DingyUser user;
	//@Column (name = "rating")
	private int value;
	
	public DingyRating(DingyUser user, int value){
		this.userId = user.getId();
		this.user = user;
		this.value=  value;
	}

}
