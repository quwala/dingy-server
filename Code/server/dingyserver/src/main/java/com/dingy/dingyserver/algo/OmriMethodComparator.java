package com.dingy.dingyserver.algo;

import java.util.Comparator;
import java.util.Map;

import com.dingy.dingyserver.datamodels.DingyUser;

public class OmriMethodComparator implements Comparator<DingyUser>{

	private Map<DingyUser, Integer> ratings;
	private Map<DingyUser,Integer> balances;
	
	public OmriMethodComparator(Map<DingyUser, Integer> ratings, Map<DingyUser,Integer> balances){
		
	}

	@Override
	public int compare(DingyUser u1, DingyUser u2) {
		
	return ratings.get(u1)+balances.get(u1) - ratings.get(u2)+balances.get(u2);
			
	}
	
	
	

}
