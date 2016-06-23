package be.wereldwijnen.services;

import be.wereldwijnen.DAO.BestelBonDAO;
import be.wereldwijnen.entities.BestelBon;

public class BestelBonService {
	private final BestelBonDAO bestelbonDAO= new BestelBonDAO();
	public BestelBonService() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(BestelBon bestelbon){
		bestelbonDAO.beginTransaction();
		bestelbonDAO.create(bestelbon);
		bestelbonDAO.commit();
	}

}
