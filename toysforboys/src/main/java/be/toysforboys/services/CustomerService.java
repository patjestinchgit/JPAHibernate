package be.toysforboys.services;

import java.util.List;

import be.toysforboys.DAO.CustomerDAO;
import be.toysforboys.DAO.OrderDAO;
import be.toysforboys.entities.Customer;
import be.toysforboys.entities.Order;

public class CustomerService {

	private final CustomerDAO customerDAO= new CustomerDAO();
	public CustomerService(){
		
	}
	
	public Customer read(long id){
		return customerDAO.read(id);
	}
	
	
	public List<Customer> findAll(){
		return customerDAO.findAll();
	}

}
