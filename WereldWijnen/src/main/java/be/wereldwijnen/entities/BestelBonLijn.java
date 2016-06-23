package be.wereldwijnen.entities;

import java.io.Serializable;
import java.util.Set;






import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="bestelbonlijnen")
public class BestelBonLijn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	protected BestelBonLijnPK bestelbonlijnpk;
	
	@Column(name="aantal")
	private int aantal;
	
	@JoinColumn(name="wijnid", referencedColumnName="id", insertable= false, updatable=false)
	@ManyToOne(optional = false)
    private Wijn wijn;
	@JoinColumn(name="bonid", referencedColumnName="id", insertable= false, updatable=false)
	@ManyToOne(optional = false)
    private BestelBon bestelbon;
	
	public BestelBonLijn(BestelBon bestelbon, Wijn wijn, int hoeveel){
		this.aantal=hoeveel;
		this.bestelbon=bestelbon;
		this.wijn=wijn;
	}
	protected BestelBonLijn(){}
	public BestelBonLijnPK getBestelbonlijnPK() {
		return bestelbonlijnpk;
	}
	public void setBestelbonlijnPK(BestelBonLijnPK bestelbonlijnPK) {
		this.bestelbonlijnpk = bestelbonlijnPK;
	}
	public int getAantal() {
		return aantal;
	}
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	public Wijn getWijn() {
		return wijn;
	}
	public void setWijn(Wijn wijn) {
		this.wijn = wijn;
	}
	public BestelBon getBestelbon() {
		return bestelbon;
	}
	public void setBestelbon(BestelBon bestelbon) {
		this.bestelbon = bestelbon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}