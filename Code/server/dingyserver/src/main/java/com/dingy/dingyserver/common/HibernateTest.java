package com.dingy.dingyserver.common;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dingy.dingyserver.datamodels.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails u = new UserDetails();
		u.setCreated(new Date());
		u.setId("dsafsajk53lmk32lg23mklg23");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		System.out.println("lolo");
	}

}
