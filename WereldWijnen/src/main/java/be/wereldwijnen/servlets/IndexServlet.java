package be.wereldwijnen.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.services.LandService;
import be.wereldwijnen.services.MandjeService;
import be.wereldwijnen.services.SoortService;
import be.wereldwijnen.servlets.mandje.Mandje;

//enkele imports
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String REDIRECT_URL="%s/land/soorten.htm?id=%d";
	public static final String REDIRECT_URL5= "%s/mandje.htm";
	private final transient LandService landservice= new LandService();
	private final transient SoortService soortservice= new SoortService();
	private final transient MandjeService mandjeservice= new MandjeService();
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("landid");
		Map<Long, Integer> winkelmandje=  (Map<Long, Integer>) request.getSession().getAttribute("mandje");
		long idbon= (long) request.getSession().getAttribute("bestelbon");
		
		System.out.println("idbon "+idbon);
		if (winkelmandje==null) {
			winkelmandje = new LinkedHashMap<>();
		}
		System.out.println("winkelmandje "+winkelmandje.toString());
		request.setAttribute("landen", landservice.findAll());
		request.setAttribute("mandje", winkelmandje);
		request.setAttribute("bestelbon", idbon);
		String mandje= request.getParameter("mandje");
		request.getSession().setAttribute("mandje", winkelmandje);
		if(id!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(),Long.parseLong(id))));
		}
		else if(mandje!=null) response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL5, request.getContextPath())));
		else request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Long, Integer> winkelmandje=  (Map<Long, Integer>) request.getSession().getAttribute("mandje");

		if (winkelmandje==null) {
			winkelmandje = new LinkedHashMap<>();
		}
		request.getSession().setAttribute("mandje", winkelmandje);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
