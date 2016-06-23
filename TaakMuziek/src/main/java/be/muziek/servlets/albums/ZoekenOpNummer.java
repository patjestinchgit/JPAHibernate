package be.muziek.servlets.albums;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.muziek.entity.Album;
import be.muziek.entity.Track;
import be.muziek.services.AlbumService;

/**
 * Servlet implementation class ZoekenOpNummer
 */
@WebServlet("/albums/zoekenopnummer.htm")
public class ZoekenOpNummer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/albums/zoekenopnummer.jsp";
	private final transient AlbumService albumservice= new AlbumService();
	BigDecimal totaletijd= new BigDecimal(0);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id= request.getParameter("albumid");
		if(id!=null){
			System.out.println("albumid= "+id);
			//ophalen soundtracks
			Album album= albumservice.readAlbum(Long.parseLong(id));
			totaletijd= album.getTijd();
			request.setAttribute("album",album);
			request.setAttribute("tijd",totaletijd);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
