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
@Table(name="productlines")
public class ProductLine implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@OneToMany(mappedBy="productline")
	private Set<Product> products;
	private String name;
	private String description;
	
	public ProductLine(String name,String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}

	public void addProduct(Product product){
		products.add(product);
		if(product.getProductline()!=this){
			product.setProductline(this);
		}
	}
	
	public void removeProduct(Product product){
		products.remove(product);
		if(product.getProductline()==this){
			product.setProductline(null);
		}
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	protected ProductLine() {
		// TODO Auto-generated constructor stub
	}

}
