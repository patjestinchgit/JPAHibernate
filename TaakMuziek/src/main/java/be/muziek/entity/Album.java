package be.muziek.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;


@Entity
@Table(name="albums")
@NamedEntityGraphs({
	@NamedEntityGraph(name = "Album.metArtiest", attributeNodes = @NamedAttributeNode("artiest"))
})


public class Album implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artiestid") 
	private Artiest artiest;

	private String naam;
	public Album(String naam, Artiest artiest) {
		// TODO Auto-generated constructor stub
		setNaam(naam);
		setArtiest(artiest);

	}

	@ElementCollection
	@CollectionTable(name="tracks", joinColumns=@JoinColumn(name = "albumid"))
	private Set<Track> tracks;
	// en getters voor id, naam, artiest en tracks
	public BigDecimal getTijd() {
		BigDecimal tijd = BigDecimal.ZERO;
		for (Track track : tracks) {
			tijd=tijd.add(track.getTijd());
		}
		return tijd;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setArtiest(Artiest artiest){
		if(this.artiest!= null && this.artiest.getAlbums().contains(this)){
			this.artiest.removeAlbum(this);
		}
		this.artiest=artiest;
		if(artiest != null && ! artiest.getAlbums().contains(this)){
			artiest.addAlbum(this);
		}
	}

	public Artiest getArtiest() {
		return artiest;
	}


	public void setNaam(String naam) {
		this.naam = naam;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", artiest=" + artiest + ", naam=" + naam+ "]";
	}
	protected Album(){}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNaam() {
		return naam;
	}
	public Set<Track> getTracks() {
		return tracks;
	}
	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}
	
}
