package br.com.jkavdev.mysql_hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaCommonActions {

	public static EntityManagerFactory factory;
	public static EntityManager manager;
	public static EntityTransaction transaction;

	public static EntityManagerFactory createFactory(String factoryName) {
		factory = Persistence.createEntityManagerFactory(factoryName);
		return factory;
	}

	public static EntityManager geEntityManager() {
		manager = factory.createEntityManager();
		return manager;
	}

	public static void beginTransaction() {
		transaction = manager.getTransaction();
		transaction.begin();
	}

	public static void commitTransaction() {
		transaction.commit();
	}

	public static void closeAll() {
		if (manager.isOpen()) {
			manager.close();
		}
		factory.close();
	}

}
