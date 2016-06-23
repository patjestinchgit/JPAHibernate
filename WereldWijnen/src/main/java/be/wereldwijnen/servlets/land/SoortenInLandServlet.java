package be.wereldwijnen.servlets.land;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.entities.Land;
import be.wereldwijnen.entities.Soort;
import be.wereldwijnen.services.BestelBonService;
import be.wereldwijnen.services.LandService;
import be.wereldwijnen.services.MandjeService;
import be.wereldwijnen.services.SoortService;

@WebServlet("/land/soorten.htm")
public class SoortenInLandServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final transient LandService landservice= new LandService();
	private final transient SoortService soortservice= new SoortService();
	private final transient MandjeService mandjeservice= new MandjeService();
	private final transient BestelBonService bestelbonservice= new BestelBonService();
	public static final String REDIRECT_URL = "%s/land/soort/wijnen.htm?idland=%d&idsoort=%d";
	public static final String REDIRECT_URL5= "%s/mandje.htm";
	public static final String VIEW = "/WEB-INF/JSP/land/soorten.jsp";
	public SoortenInLandServlet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("landen", landservice.findAll());
		Map<Long, Integer> winkelmandje=  (Map<Long, Integer>) request.getSession().getAttribute("mandje");

		if (winkelmandje==null) {
			winkelmandje = new LinkedHashMap<>();
		}
		String id="";
		String idsoort= request.getParameter("soortid");
		String idland= request.getParameter("landid");
		String mandje= request.getParameter("mandje");
		request.getSession().setAttribute("mandje", winkelmandje);
		request.setAttribute("mandje", winkelmandje);
		if(idsoort!=null){
			id=(request.getQueryString());
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(),uitHalenID(idland),uitHalenID(idsoort))));
		}
		else if(mandje!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL5, request.getContextPath())));
		}

		else if(request.getQueryString() != null) {
			id=(request.getQueryString()); 
			long idl= uitHalenID(id);
			try {
				Land soorten= landservice.read(idl);
				request.setAttribute("land",landservice.read(idl));
			} catch (NumberFormatException ex) {
				request.setAttribute("fouten",
						Collections.singletonMap("id", "tik een getal"));
			}
			request.getRequestDispatcher(VIEW).forward(request, response);
		}

	}

	private long uitHalenID(String id) {
		// TODO Auto-generated method stub
		String returnwaarde="";
		for(int i=0; i<id.length();i++){
			if((id.charAt(i)>=48) && (id.charAt(i)<=57)){
				returnwaarde=returnwaarde+id.charAt(i);
			}
		}
		System.out.println("returnwaarde "+returnwaarde);
		return Long.parseLong(returnwaarde);
	}


}
