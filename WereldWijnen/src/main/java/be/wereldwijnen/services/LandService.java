package be.wereldwijnen.services;

import java.util.List;

import be.wereldwijnen.DAO.LandDAO;
import be.wereldwijnen.entities.Land;

public class LandService {

	private final LandDAO landDAO= new LandDAO();
	
	public List<Land> findAll(){
		return landDAO.findAll();
	}

	public Land read(long id) {
		// TODO Auto-generated method stub
		return landDAO.read(id);
	}

}
