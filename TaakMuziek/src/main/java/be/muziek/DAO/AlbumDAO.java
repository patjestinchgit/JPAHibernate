package be.muziek.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import be.muziek.entity.Album;
import be.muziek.filters.JPAFilter;

public class AlbumDAO extends AbstractDAO{

	public AlbumDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Album read(long id) { // voor later in de cursus
		return getEntityManager().find(Album.class, id);
	}
	
	public List<Album> findAllAlbums(){
		return getEntityManager().createNamedQuery("Album.findAllAlbums", Album.class).getResultList();
	}

}
