package com.supermarket;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * This class is the Common SessionFactory class for this Project.
 * 
 * @author MukulJaiswal
 *
 */
public class ConfigureSetting {

	/**
	 * Returns the session for Database connectivity.
	 * @return session
	 */
	Session database() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Items.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		return session;
	}
}
