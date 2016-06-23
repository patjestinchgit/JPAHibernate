package be.toysforboys.services;

import java.util.List;

import be.toysforboys.DAO.CountrieDAO;
import be.toysforboys.DAO.OrderDAO;
import be.toysforboys.entities.Countrie;
import be.toysforboys.entities.Order;

public class CountrieService {
	private final CountrieDAO countrieDAO= new CountrieDAO();
	public CountrieService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Countrie read(long id){
		return countrieDAO.read(id);
	}
	
	
	public List<Countrie> findAll(){
		return countrieDAO.findAll();
	}
}
