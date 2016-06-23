package be.wereldwijnen.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;


@Entity
@Table(name="bestelbonnen")
public class BestelBon implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Date besteld;
	private String naam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	private int bestelwijze;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bestelbon")
    private Collection<BestelBonLijn> bestelbonlijnen;
	
	
	public BestelBon(Date besteld, String naam, String straat,
			String huisNr, String postCode, String gemeente, int bestelwijze) {
		super();
		this.besteld = besteld;
		this.naam = naam;
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postCode;
		this.gemeente = gemeente;
		this.bestelwijze = bestelwijze;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getBesteld() {
		return besteld;
	}


	public void setBestelid(Date besteld) {
		this.besteld = besteld;
	}


	public String getNaam() {
		return naam;
	}


	public void setNaam(String naam) {
		this.naam = naam;
	}


	public String getStraat() {
		return straat;
	}


	public void setStraat(String straat) {
		this.straat = straat;
	}


	public String getHuisNr() {
		return huisNr;
	}


	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	public String getGemeente() {
		return gemeente;
	}


	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}


	public int getBestelwijze() {
		return bestelwijze;
	}


	public void setBestelwijze(int bestelwijze) {
		this.bestelwijze = bestelwijze;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public Collection<BestelBonLijn> getBestelbonlijnen() {
		return bestelbonlijnen;
	}


	public void setBestelbonlijnen(Collection<BestelBonLijn> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}
	
	
	public void addBestelbonlijn(BestelBonLijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}
	
	public static boolean isStringValid(String woord){
		return woord != null && !woord.isEmpty();
	}
	
	public static Boolean isPostcodeValid(String postcode){
		if (postcode.matches("^\\d{4}$"))/* && postcode.length() == 4)*/ {
			return true;
		}
		return false;
	}
	public static Boolean isHuisnummerValid(String huisnummer){
		String stringpattern="^\\d+[a-zA-Z]*$";
		Pattern pattern= Pattern.compile(stringpattern);
		if(huisnummer.matches(stringpattern)){
			return true;
		}
		return false;
	}
	public static Boolean isGemeenteValid(String gemeente){
		String stringpattern ="^[^0-9]+$";
		return gemeente.matches(stringpattern);
	}
	protected BestelBon(){}
	

}
