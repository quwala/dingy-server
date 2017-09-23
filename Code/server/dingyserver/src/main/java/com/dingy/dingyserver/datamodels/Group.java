package com.dingy.dingyserver.datamodels;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dingy.dingyserver.common.VotingPolicy;

public class Group {
	private String name;
	private Date date_created;
	
	private List<DingyUser> members;
	
	//how much does every member owe?
	private Map<DingyUser, Integer> money_balances;
	
	
	//a negative task balance means the user is in debt in terms of task completion.
	private Map<DingyUser, Integer> task_balances;
	
	
	private List<Task> uncompleted_tasks;//currently active tasks (haven't been completed)
	private List<Task> completed_tasks;//used for task history
	
	private List<DingyTransaction> transactions;//used for transaction history
	
	private VotingPolicy voting_policy;//can every admin initiate a voting phase unilaterally or does he need a majority vote
	
	
	
}
