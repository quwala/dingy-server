package com.dingy.dingyserver.hibernate_repositories.db.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


public abstract class PlayerSpecification {
	
	
	public static Specifiable getNinIdSpec(int ninId) {
		return new AbstractSpecification() {
			@Override
			public Criterion toCriterion() {
				return Restrictions.eq("ninId", ninId);
			}			
		};	
	}
	

	public static Specifiable getServerId(int serverId) {
		
		return new AbstractSpecification() {			
			@Override
			public Criterion toCriterion() {
				return Restrictions.eq("serverId", serverId);
			}			
		};	
	}
}
