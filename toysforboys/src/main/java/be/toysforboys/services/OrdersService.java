package be.toysforboys.services;

//import java.util.Date;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import be.toysforboys.DAO.OrderDAO;
import be.toysforboys.entities.Order;
import be.toysforboys.entities.OrderDetails;
import be.toysforboys.entities.Product;
import be.toysforboys.enums.Status;

public class OrdersService {
	private final OrderDAO orderDAO= new OrderDAO();
	private final ProductServices productservices= new ProductServices();
	public OrdersService(){
	}
	
	public Order read(long id){
		return orderDAO.read(id);
	}
	
	
	public List<Order> findAll(){
		return orderDAO.findAll();
	}

	public void addaptToShipping(List<Order> listorders, Date shippeddate) {
		// TODO Auto-generated method stub
		for(int i=0; i<listorders.size();i++){
			Order order= listorders.get(i);
			order.setShippeddate(shippeddate);
			order.setStatus(Status.SHIPPED);
			LinkedList<OrderDetails> orderdetailslist= new LinkedList<OrderDetails>();
			orderdetailslist=getListDetails(order);
			
			
			for(int j=0; j<orderdetailslist.size();j++){
				int quantity= orderdetailslist.get(j).getQuantityordered();
				Product product= orderdetailslist.get(j).getProduct();
				productservices.adaptQuantity(product, quantity);
			}
			orderDAO.beginTransaction();
			orderDAO.adaptToShippingP(order);
			orderDAO.commit();
		}
		
	}

	private LinkedList<OrderDetails> getListDetails(Order order) {
		// TODO Auto-generated method stub
		Set<OrderDetails> listorderdetails= order.getOrderDetails();
		LinkedList<OrderDetails> orderdetailslisttwo= new LinkedList<OrderDetails>();
		Iterator<OrderDetails> iterator= listorderdetails.iterator();
		while(iterator.hasNext()) {
			OrderDetails orderdetails= iterator.next();
			orderdetailslisttwo.add(orderdetails);
		}
		return orderdetailslisttwo;
	}
}
