package be.toysforboys.DAO;

import java.util.List;

import be.toysforboys.entities.Countrie;
import be.toysforboys.entities.Order;

public class CountrieDAO extends AbstractDAO{

	public List<Countrie> findAll(){
		return getEntityManager().createNamedQuery("Countrie.findAll" , Countrie.class).getResultList();
	}
	public Countrie read(long id){
		return getEntityManager().find(Countrie.class, id);
	}
	public CountrieDAO() {
		// TODO Auto-generated constructor stub
	}

}
