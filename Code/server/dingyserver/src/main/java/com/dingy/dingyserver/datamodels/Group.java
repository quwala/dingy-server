package com.dingy.dingyserver.datamodels;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.dingy.dingyserver.common.VotingPolicy;

public class Group {
	private String name;
	private Date date_created;
	
	private Set<DingyUser> members;
	
	//how much does every member owe?
	private Map<DingyUser, Integer> money_balances;
	
	
	//a negative task balance means the user is in debt in terms of task completion.
	private Map<DingyUser, Integer> task_balances;
	
	
	private Set<Task> uncompleted_tasks;//currently active tasks (haven't been completed)
	private Set<Task> completed_tasks;//used for task history
	
	private VotingPolicy voting_policy;//can every admin initiate a voting phase unilaterally or does he need a majority vote
	

	
}
