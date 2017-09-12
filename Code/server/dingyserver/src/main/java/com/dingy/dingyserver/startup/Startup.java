package com.dingy.dingyserver.startup;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dingy.dingyserver.datamodels.DingyUser;

public class Startup {

	private static SessionFactory sf;

	public static void main(String[] args) {

		startup();
		
	}
	
	
	public static boolean startup(){
		if(initSessionFactory()){
			System.out.println("Session Factory initiated successfully");
			return true;
		}
		else
			System.out.println("Session Factory initialization failed");
		return false;
	}
	
	

	private static boolean initSessionFactory(){

		try {
			Configuration configuration = new Configuration().configure();


			sf = configuration.buildSessionFactory();
		}
		catch (HibernateException ex) {
			System.err.println("Session factory initialization failed. " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}


		Session session = sf.openSession();

		try {
			session.createSQLQuery("SELECT 1;").list();
			System.out.println("Connection initialization is completed.");
			return true;
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		System.out.println("initialization failed");
		return false;		


	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}




	public static void closeSessionFactory() {
		sf.close();
		System.out.println("Connection and Session Factory is closed.");
	}

}
