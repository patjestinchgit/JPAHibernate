package be.wereldwijnen.entities;

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
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedAttributeNode;

@Entity
@Table(name="soorten")
@NamedEntityGraph(name="Soort.metLand",attributeNodes= @NamedAttributeNode("land"))
public class Soort implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String naam;
	@ManyToOne(fetch = FetchType.LAZY, optional = false) 
	@JoinColumn(name = "landid") 
	private Land land;
	@OneToMany(mappedBy="soort")
	private Set<Wijn> wijnen;
	
	public Set<Wijn> getWijnen(){
		return Collections.unmodifiableSet(wijnen);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Land getLand() {
		return land;
	}
	
	public void setLand(Land land){
		if(this.land!= null && this.land.getSoorten().contains(this)){
			this.land.removeSoort(this);
		}
		this.land=land;
		if(land != null && ! land.getSoorten().contains(this)){
			land.addSoort(this);
		}
	}
	
	public void addWijn(Wijn wijn){
		wijnen.add(wijn);
		if(wijn.getSoort()!=this){
			wijn.setSoort(this);
		}
	}
	
	public void removeWijn(Wijn wijn){
		wijnen.remove(wijn);
		if(wijn.getSoort()==this){
			wijn.setSoort(null);
		}
	}
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof Soort)){
			return false;
		}
		Soort anderesoort= (Soort) object;
		return naam.equalsIgnoreCase(anderesoort.naam);
	}
	
	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}
	
	public Soort(String naam, Land land) {
		// TODO Auto-generated constructor stub
		setNaam(naam);
		setLand(land);
	}
	public void setNaam(String naam) {
		// TODO Auto-generated method stub
		this.naam= naam;
	}
	protected Soort(){}

}
