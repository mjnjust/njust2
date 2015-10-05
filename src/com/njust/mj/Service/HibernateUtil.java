package com.njust.mj.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	static Session session = null;
	static SessionFactory sessionFactory = null;
	static {
		Configuration configuration = new Configuration().configure();
		@SuppressWarnings("unused")
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();

	}
	public static Session currentSession() {
		
		return session;
	
	}
	
}
