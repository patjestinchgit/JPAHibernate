package be.wereldwijnen.DAO;

import java.util.List;

import be.wereldwijnen.entities.Land;

public class LandDAO extends AbstractDAO{
	public List<Land> findAll(){
		return getEntityManager().createNamedQuery("Land.findAll",Land.class).getResultList();
	}
	public Land read(long id){
		return getEntityManager().find(Land.class, id);
	}	
}
