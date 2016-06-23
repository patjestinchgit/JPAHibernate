package be.toysforboys.DAO;

import javax.persistence.EntityManager;

import be.toysforboys.filters.JPAFilter;


public class AbstractDAO {

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
