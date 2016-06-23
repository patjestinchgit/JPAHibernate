package be.wereldwijnen.DAO;

import java.util.List;

import javax.persistence.NoResultException;

import be.wereldwijnen.entities.Land;
import be.wereldwijnen.entities.Soort;

public class SoortDAO extends AbstractDAO{

	public Soort read(long id){
		return getEntityManager().find(Soort.class, id);
	}
	
	public List<Soort> findAll(long id){
		return getEntityManager().createNamedQuery("Soort.findAll",Soort.class).setParameter("id", id).getResultList();	
	}
	
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
