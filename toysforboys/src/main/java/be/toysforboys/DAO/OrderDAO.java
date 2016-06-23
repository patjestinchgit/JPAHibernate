package be.toysforboys.DAO;

import java.util.Date;
import java.util.List;

import be.toysforboys.entities.Order;

public class OrderDAO extends AbstractDAO{
	public List<Order> findAll(){
		return getEntityManager().createNamedQuery("Order.findAll" , Order.class).setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Order.metProducts")).getResultList();
	}
	public Order read(long id){
		return getEntityManager().find(Order.class, id);
	}
	public OrderDAO() {
		// TODO Auto-generated constructor stub
	}
	public void adaptToShipping(Order order, java.sql.Date shippeddate1) {
		// TODO Auto-generated method stub
		System.out.println("shippeddate 20 "+shippeddate1+" id "+order.getId());
		getEntityManager().createNamedQuery("Order.adaptToShipping", Order.class).setParameter("shippeddate", shippeddate1).setParameter("idveranderd", order.getId()).executeUpdate();
	}
	public void adaptToShippingP(Order order) {
		// TODO Auto-generated method stub
		getEntityManager().merge(order);
	}

}
