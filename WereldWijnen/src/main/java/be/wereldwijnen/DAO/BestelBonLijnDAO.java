package be.wereldwijnen.DAO;

import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.entities.BestelBonLijn;

public class BestelBonLijnDAO extends AbstractDAO{

	public BestelBonLijnDAO() {
		// TODO Auto-generated constructor stub
	}
	public void create(BestelBonLijn bestelbonlijn){
		getEntityManager().persist(bestelbonlijn);
	}
}
