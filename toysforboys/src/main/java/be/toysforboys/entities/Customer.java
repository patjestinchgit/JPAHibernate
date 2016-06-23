package be.toysforboys.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
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

@Entity
@Table(name="customers")
@NamedEntityGraph(name="Customer.metCountrie",attributeNodes=@NamedAttributeNode("countrie"))
public class Customer implements Serializable {
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String name;
	private String streetandnumber;
	private String city;
	private String state;
	private String postalcode;
	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@JoinColumn(name= "countryId")
	private Countrie countrie;
	@OneToMany(mappedBy="customer")
	private Set<Order> orders;
	public Customer( String name, String streetandnumber, String city,
			String state, String postalcode, Countrie countrie) {
		super();
		this.name = name;
		this.streetandnumber = streetandnumber;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.countrie = countrie;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreetandnumber() {
		return streetandnumber;
	}
	public void setStreetandnumber(String streetandnumber) {
		this.streetandnumber = streetandnumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public Countrie getCountrie() {
		return countrie;
	}
	public void setCountrie(Countrie countrie) {
		if(this.countrie!=null && this.countrie.getCustomers().contains(this)){
			this.countrie.removeCustomer(this);
		}
		this.countrie=countrie;
		if(countrie != null && !countrie.getCustomers().contains(this)){
			countrie.addCustomer(this);
		}
	}
	
	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}
	public void addOrder(Order order) {
		orders.add(order);
		if (order.getCustomer() != this) { // als de andere kant nog niet bijgewerkt is
			order.setCustomer(this); // werk je de andere kant bij
		}
	}
	public void removeOrder(Order order) {
		orders.remove(order);
		if (order.getCustomer() == this) { // als de andere kant nog niet bijgewerkt is
			order.setCustomer(null); // werk je de andere kant bij
		}
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	protected Customer(){}

}
