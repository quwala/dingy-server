package com.dingy.dingyserver.datamodels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DingyRating {
	
	@Id
	private String userId;
	
	private DingyUser user;
	private int value;
	
	public DingyRating(DingyUser user, int value){
		this.userId = user.getId();
		this.user = user;
		this.value=  value;
	}

}
