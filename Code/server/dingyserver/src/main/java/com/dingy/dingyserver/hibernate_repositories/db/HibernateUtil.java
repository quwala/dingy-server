package com.dingy.dingyserver.hibernate_repositories.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();
    
  
	public static boolean initSessionFactory() {
		
		Session session = sessionFactory.openSession();
		
		try {
			session.createSQLQuery("SELECT 1;").list();
			System.out.println("Connection initialization is completed.");
			return true;
		} catch (RuntimeException ex) {
			System.out.println(ex);
		} finally {
			session.close();
		}
		
		System.out.println("Connection initialization failed.");
		return false;		
	}
    
	/**
	 * 取得 Hibernate Session Factory
	 * 
	 * @return Hibernate Session Factory instance
	 */
    public static SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
    
    /**
     * 應用結束時關閉 Session Factory
     */
    public static void closeSessionFactory() {
		sessionFactory.close();
		System.out.println("Connection and Session Factory is closed.");
    }
    
    /**
     * 根據 hibernate.cfg.xml 設定檔建立 Session Factory 
     * 
     * @return Hibernate Session Factory instance
     */
    private static SessionFactory buildSessionFactory() {
    	
        try {
            // 指定設定檔位置 hibernate.cfg.xml
        	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        	StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        	StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
        	return configuration.buildSessionFactory(standardServiceRegistry);
        }
        catch (HibernateException ex) {
            System.err.println("Session factory initialization failed. " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
        
    }
	
}
