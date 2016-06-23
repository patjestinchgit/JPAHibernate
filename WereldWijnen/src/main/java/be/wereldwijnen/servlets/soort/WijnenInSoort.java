package be.wereldwijnen.servlets.soort;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.wereldwijnen.services.LandService;
import be.wereldwijnen.services.MandjeService;
import be.wereldwijnen.services.SoortService;
import be.wereldwijnen.services.WijnService;

/**
 * Servlet implementation class WijnenInSoort
 */
@WebServlet("/land/soort/wijnen.htm")
public class WijnenInSoort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient LandService landservice= new LandService();
	private final transient WijnService wijnservice= new WijnService();
	private final transient SoortService soortservice= new SoortService();
	private final transient MandjeService mandjeservice= new MandjeService();
	public static final String REDIRECT_URL1 = "%s/land/soort/wijnen.htm?idland=%d&idsoort=%d";
	public static final String REDIRECT_URL2= "%s/land/soort/wijn.htm?idland=%d&idwijn=%d";
	public static final String REDIRECT_URL3= "%s/land/soorten.htm?id=%d";
	public static final String REDIRECT_URL5= "%s/mandje.htm";
	public static final String VIEW = "/WEB-INF/JSP/soort/wijnen.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WijnenInSoort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathid= (request.getQueryString());
		String[] parts= uitHalenIDS(pathid);
		request.setAttribute("landen", landservice.findAll());
		Map<Long, Integer> winkelmandje=  (Map<Long, Integer>) request.getSession().getAttribute("mandje");

		if (winkelmandje==null) {
			winkelmandje = new LinkedHashMap<>();
		}
		request.getSession().setAttribute("mandje", winkelmandje);
		request.setAttribute("mandje", winkelmandje);
		if(parts.length>=1){
			String pathidl= parts[0];
			request.setAttribute("land",landservice.read(uitHalenID(pathidl)));
		}
		if(parts.length>=2){
			String pathids= parts[1];
			request.setAttribute("soort", soortservice.read(uitHalenID(pathids)));
			
		}
		String idsoort= request.getParameter("soortid");
		String idland= request.getParameter("landid");
		String idwijn= request.getParameter("wijnid");
		String mandje= request.getParameter("mandje");
		
		if((idland!=null) && (idsoort==null) && (idwijn==null) && (mandje==null)){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL3, request.getContextPath(),Long.parseLong(idland))));
		}
		else if((idsoort!=null)&&(idwijn==null)&&(mandje==null)){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL1, request.getContextPath(),Long.parseLong(idland),Long.parseLong(idsoort))));
		}
		else if(idwijn!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL2, request.getContextPath(),Long.parseLong(idland),Long.parseLong(idwijn))));
		}
		else if(mandje!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL5, request.getContextPath())));
		}
		else request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private String [] uitHalenIDS(String pathid){
		return pathid.split("&");
	}
	
	private long uitHalenID(String id) {
		// TODO Auto-generated method stub
		String returnwaarde="";
		for(int i=0; i<id.length();i++){
			if((id.charAt(i)>47) && (id.charAt(i)<58)){
				returnwaarde=returnwaarde+id.charAt(i);
			}
		}
		return Long.parseLong(returnwaarde);
	}

}
