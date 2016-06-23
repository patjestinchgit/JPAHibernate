package be.muziek.DAO;

import javax.persistence.EntityManager;

import be.muziek.filters.JPAFilter;

// enkele imports ...
abstract class AbstractDAO {
	protected EntityManager getEntityManager() { 
		return JPAFilter.getEntityManager(); 
	}
	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}
	public void commit() {
		getEntityManager().getTransaction().commit();
	}
	public void rollback() {
		getEntityManager().getTransaction().rollback();
	}
}
