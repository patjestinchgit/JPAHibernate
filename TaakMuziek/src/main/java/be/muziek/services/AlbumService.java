package be.muziek.services;

import java.util.List;

import be.muziek.DAO.AlbumDAO;
import be.muziek.entity.Album;

public class AlbumService {
	private final AlbumDAO albumDAO= new AlbumDAO();
	public AlbumService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Album> findAllAlbums() {
		return albumDAO.findAllAlbums();
	}
	
	public Album readAlbum(long id){
		return albumDAO.read(id);
	}

}
