package be.toysforboys.services;

import java.util.List;

import be.toysforboys.DAO.OrderDetailsDAO;
import be.toysforboys.entities.OrderDetails;

public class OrderdetailsService {
	private transient OrderDetailsDAO orderdetailsDAO= new OrderDetailsDAO();
	public OrderdetailsService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<OrderDetails> findByOrder(long orderid){
		return orderdetailsDAO.findByOrder(orderid);
	}
}
