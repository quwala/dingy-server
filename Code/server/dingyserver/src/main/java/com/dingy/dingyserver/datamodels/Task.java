package com.dingy.dingyserver.datamodels;

import java.util.Date;
import java.util.List;

public class Task {

	
	//basics
	private String id;
	private String name;
	private Date date_created;
	
	//user related fields
	private DingyUser created_by;
	
	private List<DingyUser> assigned_to;//the history of the users that owned this task, last element is the current owner.
	
	private DingyUser completed_by;

	private List<DingyUser> admins;//task admins can decide to initiate a voting phase, depending on the voting policy of the group
	
	
	private List<Task> completed_sub_tasks;
	private List<Task> uncompleted_sub_tasks;
	
	//does the task reset? (for example a weekly cleaning task)
	private boolean is_recurring;
	
	//how often the task will reset (i.e go into voting phase)
	private int recurrance_frequency;
	
	
	//is the task in voting phase?
	private boolean is_in_voting_phase;
	
	//the object in charge of maintaining data related to the voting phase
	private List<VotingPhase> voting_phase;
	
    
	private int difficulty;

}
