package be.wereldwijnen.DAO;

import be.wereldwijnen.entities.BestelBon;

public class BestelBonDAO extends AbstractDAO {

	public BestelBonDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(BestelBon bestelbon){
		getEntityManager().persist(bestelbon);
	}
}
