package be.toysforboys.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailsPK implements Serializable {
	private static final long serialVersionUID=1L;
	@Basic(optional = false)
    @Column(name = "orderId")
    private long orderId;
    @Basic(optional = false)
    @Column(name = "productId")
    private long productId;
	public OrderDetailsPK(long orderId, long productId) {
		// TODO Auto-generated constructor stub
		this.orderId=orderId;
		this.productId= productId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsPK other = (OrderDetailsPK) obj;
		if (orderId != other.orderId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	public OrderDetailsPK() {
		
	}
	
}
