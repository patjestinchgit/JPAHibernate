package be.wereldwijnen.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BestelBonLijnPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "bonid")
    private Long bonid;
    @Basic(optional = false)
    @Column(name = "wijnid")
    private long wijnId;
	public BestelBonLijnPK(long l, Long idwijn) {
		// TODO Auto-generated constructor stub
		this.bonid=l;
		this.wijnId=idwijn;
	}
	
	public BestelBonLijnPK() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + bonid;
		result = prime * result + wijnId;
		return (int) result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BestelBonLijnPK other = (BestelBonLijnPK) obj;
		if (bonid != other.bonid)
			return false;
		if (wijnId != other.wijnId)
			return false;
		return true;
	}
	public Long getBestelbonId() {
		return bonid;
	}
	public void setBestelbonId(int bestelbonId) {
		this.bonid = (long) bestelbonId;
	}
	public int getWijnId() {
		return (int) wijnId;
	}
	public void setWijnId(int wijnId) {
		this.wijnId = wijnId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
