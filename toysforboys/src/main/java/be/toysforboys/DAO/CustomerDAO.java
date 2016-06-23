package be.toysforboys.DAO;

import java.util.List;

import be.toysforboys.entities.Customer;
import be.toysforboys.entities.Order;

public class CustomerDAO extends AbstractDAO{

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Customer> findAll(){
		return getEntityManager().createNamedQuery("Customer.findAll" , Customer.class).setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Customer.metCountrie")).getResultList();
	}
	public Customer read(long id){
		return getEntityManager().find(Customer.class, id);
	}
	
	
}
