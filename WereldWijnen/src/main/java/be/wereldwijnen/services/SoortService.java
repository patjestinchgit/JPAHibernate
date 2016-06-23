package be.wereldwijnen.services;

import java.util.List;

import be.wereldwijnen.DAO.SoortDAO;
import be.wereldwijnen.entities.Soort;

public class SoortService {
	private final SoortDAO soortdao= new SoortDAO();
	public Long getId(){
		return soortdao.getId();
	}
	
	public List<Soort> findAll(long id){
		return soortdao.findAll(id);
	}
	
	public Soort read(long id){
		return soortdao.read(id);
	}

}
