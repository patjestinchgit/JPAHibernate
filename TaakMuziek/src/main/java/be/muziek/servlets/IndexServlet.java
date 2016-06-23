package be.muziek.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.muziek.services.AlbumService;

// enkele imports
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final transient AlbumService albumservice= new AlbumService();
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List albumlijst=  albumservice.findAllAlbums();
		System.out.println("lijst "+albumlijst.get(0));
		request.setAttribute("albums", albumservice.findAllAlbums());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}