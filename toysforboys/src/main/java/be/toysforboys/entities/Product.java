package be.toysforboys.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="products")
public class Product implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String name;
	private String scale;
	private String description;
	private long quantityinstock;
	private long quantityinorder;
	private BigDecimal buyprice;
	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@JoinColumn(name="productlineId")
	private ProductLine productline;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="order")
	private Set<OrderDetails> orderdetails;
	
	public Product(long id, String name, String scale, String description,
			long quantityinstock, long quantityinorder, BigDecimal buyprice,
			ProductLine productline) {
		super();
		this.id = id;
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityinstock = quantityinstock;
		this.quantityinorder = quantityinorder;
		this.buyprice = buyprice;
		this.productline = productline;
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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantityinstock() {
		return quantityinstock;
	}

	public void setQuantityinstock(long quantityinstock) {
		this.quantityinstock = quantityinstock;
	}

	public long getQuantityinorder() {
		return quantityinorder;
	}

	public void setQuantityinorder(long quantityinorder) {
		this.quantityinorder = quantityinorder;
	}

	public BigDecimal getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(BigDecimal buyprice) {
		this.buyprice = buyprice;
	}

	public ProductLine getProductline() {
		return productline;
	}

	public void setProductline(ProductLine productline) {
		if(this.productline != null && this.productline.getProducts().contains(this)){
			this.productline.removeProduct(this);
		}
		this.productline=productline;
		if(productline != null && ! productline.getProducts().contains(this)){
			productline.addProduct(this);
		}
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
	
	
		

	public Set<OrderDetails> getOrderDetails() {
		return orderdetails;
	}

	protected Product() {
		// TODO Auto-generated constructor stub
	}

}
