package be.toysforboys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.toysforboys.enums.Status;


@Entity
@Table(name="orders")
@NamedEntityGraph(name="Order.metCustomer",attributeNodes=@NamedAttributeNode("customer"))
public class Order implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private Date orderdate;
	private Date requireddate;
	private Date shippeddate;
	private String comments;
	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@JoinColumn(name="customerId")
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="order")
	private Set<OrderDetails> orderdetails;
	
	public Order(Date orderdate, Date requireddate, Date shippeddate,
			String comments, Customer customer, Status status) {
		super();
		this.orderdate = orderdate;
		this.requireddate = requireddate;
		this.shippeddate = shippeddate;
		this.comments = comments;
		this.customer = customer;
		this.status = status;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public Date getOrderdate() {
		return orderdate;
	}




	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}




	public Date getRequireddate() {
		return requireddate;
	}




	public void setRequireddate(Date requireddate) {
		this.requireddate = requireddate;
	}




	public Date getShippeddate() {
		return shippeddate;
	}




	public void setShippeddate(Date shippeddate) {
		this.shippeddate = shippeddate;
	}




	public String getComments() {
		return comments;
	}




	public void setComments(String comments) {
		this.comments = comments;
	}




	public Customer getCustomer() {
		return customer;
	}




	public void setCustomer(Customer customer) {
		if (this.customer != null && this.customer.getOrders().contains(this)) {
			// als de andere kant nog niet bijgewerkt is
			this.customer.removeOrder(this); // werk je de andere kant bij
		}
		this.customer = customer;
		if (customer != null && ! customer.getOrders().contains(this)) {
			// als de andere kant nog niet bijgewerkt is
			customer.addOrder(this); // werk je de andere kant bij
		}
	}




	public Status getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setOrderdetails(Set<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	
	public void addOrderdetail(OrderDetails orderdetail) {
		orderdetails.add(orderdetail);
	}
	
	
		

	public Set<OrderDetails> getOrderdetails() {
		return orderdetails;
	}




	public Set<OrderDetails> getOrderDetails() {
		return orderdetails;
	}
	

	protected Order() {
		// TODO Auto-generated constructor stub
	}

}
