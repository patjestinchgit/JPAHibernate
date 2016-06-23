package be.wereldwijnen.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="wijnen")
@NamedEntityGraph(name="Wijn.metSoort",attributeNodes=@NamedAttributeNode("soort"))
public class Wijn implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int jaar;
	private int beoordeling;
	private BigDecimal prijs;
	private long inBestelling; 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="soortid")
	private Soort soort;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wijn")
    private Collection<BestelBonLijn> bestelbonlijnen;
	

	public void setBestelbonlijnen(Collection<BestelBonLijn> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}
	
	
	public void addBestelbonlijn(BestelBonLijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}
	
	
		

	public Collection<BestelBonLijn> getBestelbonlijnen() {
		return bestelbonlijnen;
	}


	public Wijn(Soort soort, int jaar, int beoordeling, BigDecimal prijs, long inbestelling) {
		// TODO Auto-generated constructor stub
		setSoort(soort);
		setJaar(jaar);
		setBeoordeling(beoordeling);
		setPrijs(prijs);
		setInbestelling(inbestelling);
		bestelbonlijnen=new LinkedHashSet<>();
	}
	
	public void setInbestelling(long inbestelling) {
		// TODO Auto-generated method stub
		this.inBestelling=inbestelling;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}


	public int getJaar() {
		return jaar;
	}


	public int getBeoordeling() {
		return beoordeling;
	}


	public BigDecimal getPrijs() {
		return prijs;
	}


	public long getInBestelling() {
		return inBestelling;
	}
	
	public void setInBestellingBij(long hoeveelheid){
		this.inBestelling+=hoeveelheid;
	}
	


	public Soort getSoort() {
		return soort;
	}
	
	
	
	public void setId(long id) {
		this.id = id;
	}


	public void setJaar(int jaar) {
		this.jaar = jaar;
	}


	public void setBeoordeling(int beoordeling) {
		this.beoordeling = beoordeling;
	}


	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public void setSoort(Soort soort){
		if(this.soort!=null && this.soort.getWijnen().contains(this)){
			this.soort.removeWijn(this);
		}
		this.soort=soort;
		if(soort != null && ! soort.getWijnen().contains(this)){
			soort.addWijn(this);
		}
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beoordeling;
		result = prime * result
				+ ((bestelbonlijnen == null) ? 0 : bestelbonlijnen.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (inBestelling ^ (inBestelling >>> 32));
		result = prime * result + jaar;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		Wijn other = (Wijn) obj;
		if (beoordeling != other.beoordeling)
			return false;
		if (bestelbonlijnen == null) {
			if (other.bestelbonlijnen != null)
				return false;
		} else if (!bestelbonlijnen.equals(other.bestelbonlijnen))
			return false;
		if (id != other.id)
			return false;
		if (inBestelling != other.inBestelling)
			return false;
		if (jaar != other.jaar)
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}


	protected Wijn(){}
	
}
