package com.dingy.dingyserver.test.hibernate;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dingy.dingyserver.datamodels.DingyUser;
import com.dingy.dingyserver.datamodels.VotingPhase;
import com.dingy.dingyserver.startup.Startup;

public class HibernateMapTests {

	private static SessionFactory sf; 
	private static Session session;

	@BeforeClass
	public static void beforeClass(){
		Startup.startup();
		sf = Startup.getSessionFactory();

	}

	@Before
	public  void before(){
		session = sf.openSession();
		session.beginTransaction();
	}

	@After
	public  void after(){
		session.getTransaction().commit();
		session.close();
	}



	@Test
	public void testVotingPolicyTableCreation(){
		assertTrue(Startup.startup());

		DingyUser user1 = new DingyUser("omri");
		DingyUser user2 = new DingyUser("roi");

		VotingPhase vp = new VotingPhase();
		vp.addRating(user1, 1);
		vp.addRating(user2, 2);



		session.save(user1);
		session.save(user2);
		session.save(vp);
		


	}

}
