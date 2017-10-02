package com.dingy.dingyserver.hibernate_repositories.db.repository;

import com.dingy.dingyserver.hibernate_repositories.db.entity.PlayerInfo;

public class PlayerInfoRepository extends AbstractRepository<PlayerInfo, Integer>{
	
	public Class<PlayerInfo> getDomainClass() {
		return PlayerInfo.class;
	}
	
}
