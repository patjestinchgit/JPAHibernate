package be.wereldwijnen.DAO;

import java.util.List;

import be.wereldwijnen.entities.Wijn;

public class WijnDAO extends AbstractDAO{

	public WijnDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Wijn read(long id){
		return getEntityManager().find(Wijn.class, id);
	}
	
	public List<Wijn> findAll(long id){
		return getEntityManager().createNamedQuery("Wijn.findAll", Wijn.class).setParameter("id",id).getResultList();
	}
	
	public void inBestellingBij(long hoeveelheid, long idwijn){
		getEntityManager().createNamedQuery("Wijn.setInBestellingBij").setParameter("idwijn", idwijn).setParameter("hoeveelheid",hoeveelheid).executeUpdate();
	}
}
