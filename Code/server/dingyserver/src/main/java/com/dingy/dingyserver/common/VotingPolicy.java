package com.dingy.dingyserver.common;


//Unilateral - any admin of the task can decide to initiate a voting phase whenever he likes.
//Majority vote - there has to be a majority of users in the task agreeing to initiate a voting phase.
public enum VotingPolicy {
	UNILATERAL("Unilateral"), MAJORITY("Majority vote");
	

    private final String description;

    private VotingPolicy(String value) {
        description = value;
    }

    public String getDescription() {
        return description;
    }
}
