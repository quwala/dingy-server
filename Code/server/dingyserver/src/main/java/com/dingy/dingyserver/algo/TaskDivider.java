package com.dingy.dingyserver.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dingy.dingyserver.common.DingyConstants;
import com.dingy.dingyserver.datamodels.DingyUser;
import com.dingy.dingyserver.datamodels.VotingPhase;

public class TaskDivider {
	
	
	public TaskDividerResult allocate(VotingPhase vp, Map<DingyUser,Integer> balances){
		
		
		return getMinimalPVal(vp, balances);
		
	}

	
	//omri's method
	private TaskDividerResult getMinimalPVal(VotingPhase vp, Map<DingyUser,Integer> balances) {
		
		Map<DingyUser, Integer> ratings = vp.getRatings();
		Integer taskAverage = vp.getTaskAverage();
		
		Set<DingyUser> usersSet = balances.keySet();
		List<DingyUser> usersList = new ArrayList<DingyUser>();
		usersList.addAll(usersSet);
		
		usersList.sort(new OmriMethodComparator(ratings,balances));
		
		DingyUser winner = usersList.get(0);
		
				
		balances.put(winner, balances.get(winner)+taskAverage);
		
		recalibrateToZeroes(balances);
		
		
		
		
		
		TaskDividerResult res = new TaskDividerResult(usersList.get(0),balances);
		return res;
	}

	private void recalibrateToZeroes(Map<DingyUser, Integer> balances) {
		Collection<Integer> balances_values = balances.values();
		int min = -1;
		
		for(Integer balance : balances_values){
			if(min==-1)
				min=balance;
			else if(balance<min)
				min=balance;
				
		}
		
		for( Entry<DingyUser, Integer> balance : balances.entrySet()){
			balance.setValue(balance.getValue()- min );
		}
		
	
		
	}


	private DingyUser getMinimalBidder(VotingPhase vp) {
		Map<DingyUser,Integer> ratings = vp.getRatings();
		
		DingyUser minimumBidder = null;
		Integer minimumBid = DingyConstants.maximumBid+1;

		for(DingyUser user : ratings.keySet())
		{
			if(ratings.get(user) < minimumBid)
			{
				minimumBid = ratings.get(user);
				minimumBidder = user;
			}
		}
		
		return minimumBidder;
	}

}
