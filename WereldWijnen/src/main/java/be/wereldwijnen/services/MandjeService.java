package be.wereldwijnen.services;

import be.wereldwijnen.DAO.MandjeDAO;
import be.wereldwijnen.entities.Mandje;

public class MandjeService {
	private final MandjeDAO mandjeDAO= new MandjeDAO();
	public MandjeService() {
		// TODO Auto-generated constructor stub
	}
	
	public Iterable<Mandje> findAll(){
		return mandjeDAO.findAll();
	}
	
	public void addMandje(Mandje mandje){
		mandjeDAO.addItem(mandje);
	}
	

}
