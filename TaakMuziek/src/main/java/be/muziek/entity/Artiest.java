package be.muziek.entity;

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
@Table(name="artiesten")

public class Artiest implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@OneToMany(mappedBy="artiest")
	private Set<Album> albums;
	
	public Set<Album> getAlbums(){
		return Collections.unmodifiableSet(albums);
	}
	
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	
	public void addAlbum(Album album){
		albums.add(album);
		if(album.getArtiest()!=this){
			album.setArtiest(this);
		}
	}
	public boolean equals(Object object) {
		if(!(object instanceof Artiest)){
			return false;
		}
		Artiest andereArtiest=(Artiest) object;
		return naam.equalsIgnoreCase(andereArtiest.naam);
	}
	public void removeAlbum(Album album){
		albums.remove(album);
		if(album.getArtiest()==this){
			album.setArtiest(null);
		}
	}
	
	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}
	
	public Artiest() {
		// TODO Auto-generated constructor stub
	}

}
