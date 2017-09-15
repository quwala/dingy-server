package com.dingy.dingyserver.datamodels;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class VotingPhase {

	private Map<DingyUser, Integer> ratings;//the rating each user in the group gave to the task
	
	private Date date_started;//when did this voting phase start
	
	private Date date_ended;//when did this voting phase end
	
	
	public Map<DingyUser,Integer> getRatings(){
		return ratings;
	}
	
	public int getTaskAverage(){
		Collection<Integer> ratings_values = getRatings().values();//the ratings values (1-5 numbers)
		int sum =0;
		for(Integer rating : ratings_values){
			sum+=rating;
		}
		return sum/ratings_values.size();
	}
}
