package be.wereldwijnen.entities;

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
@Table(name="landen")

public class Land implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String naam;
	
	@OneToMany(mappedBy="land")
	private Set<Soort> soorten;
	
	public Set<Soort> getSoorten(){
		return Collections.unmodifiableSet(soorten);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Land(String naam) {
		// TODO Auto-generated constructor stub
		setNaam(naam);
	}
	
	
	public void addSoort(Soort soort) {
		soorten.add(soort);
		if (soort.getLand() != this) {
			soort.setLand(this);
		}
	}
	public void removeSoort(Soort soort) {
		soorten.remove(soort);
		if (soort.getLand() == this) {
			soort.setLand(null);
		}
	}
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Land)) {
			return false;
		}
		Land andereLand = (Land) object;
		return naam.equalsIgnoreCase(andereLand.naam);
	}
	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}
	
	protected Land(){}
	
}
