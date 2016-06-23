package be.wereldwijnen.servlets.wijn;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.entities.BestelBonLijn;
import be.wereldwijnen.entities.Land;
import be.wereldwijnen.entities.Mandje;
import be.wereldwijnen.entities.Soort;
import be.wereldwijnen.entities.Wijn;
import be.wereldwijnen.services.LandService;
import be.wereldwijnen.services.MandjeService;
import be.wereldwijnen.services.SoortService;
import be.wereldwijnen.services.WijnService;


/**
 * Servlet implementation class WijnServlet
 */
@WebServlet("/land/soort/wijn.htm")
public class WijnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient LandService landservice= new LandService();
	private final transient WijnService wijnservice= new WijnService();
	private final transient SoortService soortservice= new SoortService();
	private final transient MandjeService mandjeservice= new MandjeService();
	
	public static final String REDIRECT_URL1 = "%s/land/soort/wijnen.htm?idland=%d&idsoort=%d";
	public static final String REDIRECT_URL2= "%s/land/soort/wijn.htm?idland=%d&idwijn=%d";
	public static final String REDIRECT_URL3= "%s/land/soorten.htm?id=%d";
	public static final String REDIRECT_URL4= "%s/index.htm";
	public static final String REDIRECT_URL5= "%s/mandje.htm";
	public static final String VIEW = "/WEB-INF/JSP/wijn/wijn.jsp";   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathid= (request.getQueryString());
		String[] parts= uitHalenIDS(pathid);
		@SuppressWarnings("unchecked")
		Map<Long, Integer> winkelmandje= (Map<Long, Integer>) request.getSession().getAttribute("mandje");
		if (winkelmandje==null) {
            winkelmandje = new LinkedHashMap<>();
        }
		request.getSession().setAttribute("mandje", winkelmandje);
		request.setAttribute("mandje", winkelmandje);
		request.setAttribute("landen", landservice.findAll());
		if(parts.length>=1){
			String pathidl= parts[0];
			request.setAttribute("land",landservice.read(uitHalenID(pathidl)));
		}
		if(parts.length>=2){
			String pathidw= parts[1];
			request.setAttribute("wijn", wijnservice.read(uitHalenID(pathidw)));
		}
		String idsoort= request.getParameter("soortid");
		String idland= request.getParameter("landid");
		String idwijn= request.getParameter("wijnid");
		String mandje= request.getParameter("mandje");
		System.out.println("mandje get parameter "+mandje);
		
		if((idsoort==null)&&(idland!=null)&&(mandje==null)){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL3, request.getContextPath(),Long.parseLong(idland))));
		}
		else if((idsoort!=null)&&(idland!=null)&&(mandje==null)){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL1, request.getContextPath(),Long.parseLong(idland),Long.parseLong(idsoort))));
		}
		else if(mandje!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL5, request.getContextPath())));
		}
		else request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> fouten= new HashMap<>();
		String pathid= (request.getQueryString());
		String[] parts= uitHalenIDS(pathid);
		int aantal=0;
		String pathidl="";
		String pathidw="";
		if(parts.length>=1){
			pathidl= parts[0];
			request.setAttribute("land",landservice.read(uitHalenID(pathidl)));
		}
		if(parts.length>=2){
			pathidw= parts[1];
			request.setAttribute("wijn", wijnservice.read(uitHalenID(pathidw)));
		}
		String idsoort= request.getParameter("soortid");
		String idland= request.getParameter("landid");
		String idwijn= request.getParameter("wijnid");
		//Winkelmandje
		Map<Long, Integer> winkelmandje=  (Map<Long, Integer>) request.getSession().getAttribute("mandje");
		
        if (winkelmandje==null) {
            winkelmandje = new LinkedHashMap<>();
        }
		try{
			aantal=(int) Long.parseLong(request.getParameter("aantalflessen"));
			if((aantal<=0)){
				fouten.put("aantal","Positief aantal ingeven");
			} 
		}catch (NumberFormatException ex) {
			fouten.put("aantal", "Positief aantal ingeven");
		}
		request.setAttribute("landen", landservice.findAll());
		if(fouten.isEmpty()){
			if(winkelmandje.containsKey(uitHalenID(pathidw))) winkelmandje.put(uitHalenID(pathidw), winkelmandje.get(uitHalenID(pathidw))+aantal);
			else winkelmandje.put(uitHalenID(pathidw), aantal);
			request.setAttribute("mandje", winkelmandje);
			request.getSession().setAttribute("mandje", winkelmandje);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL4, request.getContextPath())));
		}
		else if(!(fouten.isEmpty())){
			request.setAttribute("mandje", winkelmandje);
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
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
		System.out.println("returnwaarde "+returnwaarde);
		return Long.parseLong(returnwaarde);
	}
	
	//Gets the session Attribute
    private Object getSessionAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }

}
