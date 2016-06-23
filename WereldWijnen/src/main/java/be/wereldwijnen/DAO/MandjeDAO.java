package be.wereldwijnen.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import be.wereldwijnen.entities.Mandje;

public class MandjeDAO extends AbstractDAO{
	//HashMap<Integer, Integer> lijstmandje= new HashMap<Integer, Integer>();
	List<Mandje> lijstmandje= new LinkedList<Mandje>();
	public MandjeDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Iterable<Mandje> findAll(){
		return lijstmandje;
	}
	
	public void addItem(Mandje mandje){
		lijstmandje.add(mandje);
	}
}
