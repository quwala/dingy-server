package com.dingy.dingyserver.hibernate_repositories.db.specification;

import org.hibernate.criterion.Criterion;

import com.dingy.dingyserver.hibernate_repositories.db.Criterial;


public interface Specifiable extends Criterial {
	  
	
	public Criterion toCriterion();
	
	public Specifiable and(final Specifiable specification);

	
	public Specifiable or(final Specifiable specification);

	
	public Specifiable not(final Specifiable specification);
	
}
