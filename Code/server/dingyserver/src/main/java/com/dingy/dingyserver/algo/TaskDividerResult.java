package com.dingy.dingyserver.algo;

import java.util.Map;

import com.dingy.dingyserver.datamodels.DingyUser;

public class TaskDividerResult {
	
	private DingyUser allocated_to;
	
	private Map<DingyUser, Integer> new_balances;
	
	public TaskDividerResult(DingyUser winner, Map<DingyUser, Integer> balances){
		this.allocated_to = winner;
		this.new_balances = balances;
	}

}
