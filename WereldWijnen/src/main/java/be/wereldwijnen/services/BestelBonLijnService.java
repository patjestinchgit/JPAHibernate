package be.wereldwijnen.services;

import be.wereldwijnen.DAO.BestelBonLijnDAO;
import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.entities.BestelBonLijn;

public class BestelBonLijnService {
	private final BestelBonLijnDAO bestelbonlijnDAO= new BestelBonLijnDAO();
	public BestelBonLijnService() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(BestelBonLijn bestelbonlijn){
		bestelbonlijnDAO.beginTransaction();
		bestelbonlijnDAO.create(bestelbonlijn);
		bestelbonlijnDAO.commit();
	}
}
