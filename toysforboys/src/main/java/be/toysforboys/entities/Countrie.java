package be.toysforboys.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="countries")
public class Countrie implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@OneToMany(mappedBy="countrie")
	private Set<Customer> customers;
	private String name;
	public Countrie(String name) {
		// TODO Auto-generated constructor stub
		setName(name);
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set<Customer> getCustomers() {
		return Collections.unmodifiableSet(customers);
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
		if (customer.getCountrie() != this) { // als de andere kant nog niet bijgewerkt is
			customer.setCountrie(this); // werk je de andere kant bij
		}
	}
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
		if (customer.getCountrie() == this) { // als de andere kant nog niet bijgewerkt is
			customer.setCountrie(null); // werk je de andere kant bij
		}
	}
	
	protected Countrie(){}
	
}
