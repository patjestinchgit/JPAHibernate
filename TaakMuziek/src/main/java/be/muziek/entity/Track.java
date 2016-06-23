package be.muziek.entity;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

// enkele imports ...
@Embeddable
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;
	private String naam;
	private BigDecimal tijd;
	// en getters voor naam en tijd
	@Override
	public boolean equals(Object object) {
		if (! (object instanceof Track)) {
			return false;
		}
		return naam.equalsIgnoreCase(((Track) object).naam);
	}
	@Override
	public int hashCode() {
		return naam.toLowerCase().hashCode();
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public BigDecimal getTijd() {
		return tijd;
	}
	public void setTijd(BigDecimal tijd) {
		this.tijd = tijd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}