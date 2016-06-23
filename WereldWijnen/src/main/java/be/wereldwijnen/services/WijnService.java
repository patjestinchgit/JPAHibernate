package be.wereldwijnen.services;

import java.util.List;

import be.wereldwijnen.DAO.WijnDAO;
import be.wereldwijnen.entities.BestelBonLijn;
import be.wereldwijnen.entities.Wijn;

public class WijnService {
	private final WijnDAO wijnDAO= new WijnDAO();
	
	public List<Wijn> findAll(long id){
		return wijnDAO.findAll(id);
	}
	
	public Wijn read(long id){
		return wijnDAO.read(id);
	}
	
	public WijnService() {
		// TODO Auto-generated constructor stub
	}
	
	public void inBestellingBij(long hoeveelheid, long idwijn){
		wijnDAO.inBestellingBij(hoeveelheid, idwijn);
	}
	
	public void toevoegenBestelling(long idwijn, BestelBonLijn bestelbonlijn){
		wijnDAO.beginTransaction();
		wijnDAO.read(idwijn).addBestelbonlijn(bestelbonlijn);
		wijnDAO.commit();
	}

}
