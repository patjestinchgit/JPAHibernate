package be.toysforboys.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
@NamedEntityGraphs({
	@NamedEntityGraph(name="OrderDetails.metProducts",attributeNodes=@NamedAttributeNode("product")),
	@NamedEntityGraph(name="OrderDetails.metOrders",attributeNodes=@NamedAttributeNode("order")),
})

public class OrderDetails implements Serializable{
	private static final long serialVersionUID=1L;
	@EmbeddedId
	protected OrderDetailsPK orderdetailspk;
	@Column(name="quantityOrdered")
	private int quantityordered;
	@Column(name="priceEach")
	private BigDecimal priceeach;
	
	@JoinColumn(name="orderid", referencedColumnName="id", insertable= false, updatable= false)
	@ManyToOne(optional= false)
	private Order order;
	
	@JoinColumn(name="productid", referencedColumnName="id", insertable= false, updatable=false)
	@ManyToOne(optional= false)
	private Product product;

	public OrderDetails(int quantityordered, BigDecimal priceeach, Order order,
			Product product) {
		super();
		this.quantityordered = quantityordered;
		this.priceeach = priceeach;
		this.order = order;
		this.product = product;
	}
	
	public OrderDetails(){}
	

	public OrderDetailsPK getOrderdetailspk() {
		return orderdetailspk;
	}

	public void setOrderdetailspk(OrderDetailsPK orderdetailspk) {
		this.orderdetailspk = orderdetailspk;
	}

	public int getQuantityordered() {
		return quantityordered;
	}

	public void setQuantityordered(int quantityordered) {
		this.quantityordered = quantityordered;
	}

	public BigDecimal getPriceeach() {
		return priceeach;
	}

	public void setPriceeach(BigDecimal priceeach) {
		this.priceeach = priceeach;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
