package com.dingy.dingyserver.hibernate_repositories.db.repository;

import com.dingy.dingyserver.hibernate_repositories.db.entity.PlayerAccount;

public class PlayerAccountRepository extends AbstractRepository<PlayerAccount, Integer>{
	
	public Class<PlayerAccount> getDomainClass() {
		return PlayerAccount.class;
	}
	
}
