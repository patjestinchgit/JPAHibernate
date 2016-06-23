package be.toysforboys.DAO;

import java.util.List;

import be.toysforboys.entities.OrderDetails;

public class OrderDetailsDAO extends AbstractDAO{

	public OrderDetailsDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<OrderDetails> findByOrder(long orderid){
		return getEntityManager().createNamedQuery("OrderDetails.findByOrderID", OrderDetails.class).setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("OrderDetails.metOrders")).getResultList();
		//.setParameter("orderid",orderid)
	}
	public OrderDetails read(long id){
		return null;
	}

}
