package com.dingy.dingyserver.datamodels;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "VOTING_PHASES")
public class VotingPhase {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
    private String id;

	
    
//    @ManyToMany
//    @JoinTable(name="GROUP_USERS_RATINGS")
//    @MapKeyColumn(name="USER_ID")
    
    @ElementCollection
    @Column (name = "rating")
	private Map<DingyUser,Integer> ratings;//the rating each user in the group gave to the task
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Map<DingyUser,Integer> getRatings(){
		return ratings;
	}
    public void setRatings(Map<DingyUser, Integer> value)
    {
    	ratings = value;
    }
	
	
	
	private Date date_started;//when did this voting phase start
	
	private Date date_ended;//when did this voting phase end
	
	public VotingPhase(){
		date_started = new Date();
		ratings = new HashMap<>();
	}
	
	

    public Date getDate_started() {
		return date_started;
	}
	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}
	public Date getDate_ended() {
		return date_ended;
	}
	public void setDate_ended(Date date_ended) {
		this.date_ended = date_ended;
	}
	
    
    
	
	public void addRating(DingyUser user, Integer value){
		ratings.put(user, value);
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
