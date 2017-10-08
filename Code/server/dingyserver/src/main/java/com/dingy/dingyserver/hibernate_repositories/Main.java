package com.dingy.dingyserver.hibernate_repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.dingy.dingyserver.hibernate_repositories.db.HibernateUtil;
import com.dingy.dingyserver.hibernate_repositories.db.Persistable;
import com.dingy.dingyserver.hibernate_repositories.db.entity.PlayerAccount;
import com.dingy.dingyserver.hibernate_repositories.db.entity.PlayerInfo;
import com.dingy.dingyserver.hibernate_repositories.db.page.Page;
import com.dingy.dingyserver.hibernate_repositories.db.page.PageRequest;
import com.dingy.dingyserver.hibernate_repositories.db.repository.PlayerAccountRepository;
import com.dingy.dingyserver.hibernate_repositories.db.sort.Sort;
import com.dingy.dingyserver.hibernate_repositories.db.sort.Sort.Direction;
import com.dingy.dingyserver.hibernate_repositories.db.specification.PlayerSpecification;
import com.dingy.dingyserver.hibernate_repositories.db.specification.Specifiable;


public class Main {
	
	
	public static void main(String[] args) {
		
		HibernateUtil.initSessionFactory();
		
		
		// 使用 PlayerAccount Repository 進行 CRUD
		PlayerAccountRepository repo = new PlayerAccountRepository();
		
	
		
		List<PlayerAccount> transientAccounts = populateTestData(48);
		// 保存 transient 狀態的 transientAccounts
		repo.save(transientAccounts);
		
				
	
		Specifiable serverTwoSpec = PlayerSpecification.getServerId(2);
		Specifiable andSpec = serverTwoSpec; 
		
		Sort descByNinId = new Sort(Direction.DESC, "ninId"); 
		
		List<PlayerAccount> queryAccounts = repo.findAll(andSpec, descByNinId);
		
		
		//show(queryAccounts);
		

		/***********************************************************
		 * Query 功能測試 2 : 使用 PageRequest 進行 Query
		 ***********************************************************/
		PageRequest pageRequest = new PageRequest(1, 10, descByNinId);
		
		Page<PlayerAccount> page = repo.findAll(pageRequest);
		
		
		
		//show(page);
		
				
		/***********************************************************
		 * update 功能測試
		 ***********************************************************/
		List<PlayerAccount> toUpdateAccounts = repo.findAll(serverTwoSpec);
		List<PlayerAccount> updatedAccounts = new ArrayList<>();
		
		for(PlayerAccount account : toUpdateAccounts) {
			String changedUsername = account.getUsername() + "更新";
			account.setUsername(changedUsername);
			updatedAccounts.add(account);
		}
		
		// 更新 detached 狀態的 changedAccounts 資料
		repo.save(updatedAccounts);
		
		// 使用 Repository 取得 update 結果
		List<PlayerAccount> queryUpdatedAccounts = repo.findAll(andSpec, descByNinId);
		
		
		//show(queryUpdatedAccounts);
		
		
		/***********************************************************
		 * delete 功能測試
		 ***********************************************************/
		// 刪除 1 服所有帳號
		List<PlayerAccount> toDeleteAccounts = repo.findAll(
				PlayerSpecification.getServerId(1)
		);
		repo.delete(toDeleteAccounts);
		
		// 使用 console 顯示 Query 結果
		List<PlayerAccount> deletedAccounts = repo.findAll();
		//show(deletedAccounts);	
		
		
		// 關閉 Session Factory
		HibernateUtil.closeSessionFactory();
	}
	
	/**
	 * 使用 Console INFO 顯示 qeury 結果
	 * 
	 * @param entities
	 */
	private static <T extends Persistable> void show(Iterable<T> entities) {
		// lambda syntax
		entities.forEach(entity -> System.out.println(entity));
	}	
	
	/**
	 * 使用 Repository 批量 persist 數筆 Query 測試用 count 筆資料
	 * 依照 count 設定伺服器(serverId)與帳號創立時間(createDate)
	 * count 為偶數時帳號設定為第 2 伺服器 奇數為第 1 伺服器
	 * count 為 0 時帳號創立時間為當下 
	 * count 為 1 時創立時間為當下時間前 1 個小時 以下依序類推
	 */
	private static List<PlayerAccount> populateTestData(int count) {
		
		List<PlayerAccount> list = new ArrayList<PlayerAccount>();
		
		for (int i=0; i<count; i++) {
			PlayerAccount ac = new PlayerAccount();
			
			// ninId 與 username 按變數 i 從 0 1 2 3 ... n 與 player0 player1 .... playern 依序命名
			ac.setUsername("player" + i);
			
			// 奇數 ninId 註冊1服 偶數 ninId 註冊2服 
			if (i%2 == 1) {
				ac.setServerId(1);
			} else {
				ac.setServerId(2);
			}
			
			// createDate 首位註冊帳號創建日期設定為現在時間 
			// 次位註冊帳號創建日期往減少1小時 以此類推
			ac.setCreateDate(new Date());
			
			// 設定 OneToOne Association Player Info Instance
			PlayerInfo info = PlayerInfo.newInstance();
			ac.setPlayerInfo(info);
			info.setPlayerAccount(ac);
			
			list.add(ac);
		}
		return list;
	} 
	
}

