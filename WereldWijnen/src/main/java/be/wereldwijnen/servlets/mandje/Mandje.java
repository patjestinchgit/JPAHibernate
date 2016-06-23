package be.wereldwijnen.servlets.mandje;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.wereldwijnen.entities.BestelBon;
import be.wereldwijnen.entities.BestelBonLijn;
import be.wereldwijnen.entities.BestelBonLijnPK;
import be.wereldwijnen.entities.Wijn;
import be.wereldwijnen.services.BestelBonLijnService;
import be.wereldwijnen.services.BestelBonService;
import be.wereldwijnen.services.LandService;
import be.wereldwijnen.services.MandjeService;
import be.wereldwijnen.services.WijnService;

/**
 * Servlet implementation class Mandje
 */
@WebServlet("/mandje.htm")
public class Mandje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient MandjeService mandjeservice= new MandjeService();
	private final transient LandService landservice= new LandService();
	private final transient WijnService wijnservice= new WijnService();
	private final transient BestelBonService bestelbonservice= new BestelBonService();
	private final transient BestelBonLijnService bestelbonlijnservice= new BestelBonLijnService();
	public static final String REDIRECT_URL4= "%s/index.htm";
	public static final String VIEW = "/WEB-INF/JSP/mandje/mandje.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Long, Integer> winkelmandje= (Map<Long, Integer>) request.getSession().getAttribute("mandje");
		String TNOZ=request.getParameter("terugnaaroverzicht");
		//Uithalen van de wijngegevens uit winkelmandje
		Map<Wijn, Integer> winkelmandAG= new LinkedHashMap();
		Set set= winkelmandje.entrySet();
		Iterator i= set.iterator();
		while(i.hasNext()){
			Map.Entry me= (Map.Entry)i.next();
			Long idwijn= (Long) me.getKey();
			int hoeveel= (int) me.getValue();
			//Lijst ophalen van wijgegevens uit database: Land/soort/jaar/prijs
			Wijn wijn= wijnservice.read(idwijn);
			winkelmandAG.put(wijn, hoeveel);
		}
		
		if(TNOZ!=null){
			request.getSession().setAttribute("mandje", winkelmandje);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL4, request.getContextPath())));
		}
		else{
			request.setAttribute("tijdelijkebestelling", winkelmandAG);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<Long, Integer> winkelmandje= (Map<Long, Integer>) request.getSession().getAttribute("mandje");
		String TNOZ=request.getParameter("terugnaaroverzicht");
		//Uithalen van de wijngegevens uit winkelmandje
		Map<Wijn, Integer> winkelmandAG= new LinkedHashMap();
		
		Map<String, String> fouten = new HashMap();
		String naam= request.getParameter("naam");
		if(! BestelBon.isStringValid(naam)){
			fouten.put("naam","verplicht");
		}//else if voor lengte van naam
		if(naam.length()<4) fouten.put("naam", "naam moet langer dan 4 karakters zijn");
		
		String straat= request.getParameter("straat");
		if(! BestelBon.isStringValid(straat)){
			fouten.put("straat",  "verplicht");
		}//else if voor lengte van straat
		if(straat.length()<4) fouten.put("straat", "straatnaam moet langer zijn dan 4 karakters");
		String huisNr= request.getParameter("huisnummer");
		if(! BestelBon.isStringValid(huisNr)){
			fouten.put("huisnummer", "verplicht");
		}//else if voor controleren dat huisnummer wel kan bestaan 15A, 15
		if(! BestelBon.isHuisnummerValid(huisNr)){
			fouten.put("huisnummer", "huisnummer kan enkel bestaan uit cijfers of cijfers en letter");
		}
		String postcode= request.getParameter("postcode");
		if(! BestelBon.isStringValid(postcode)){
			fouten.put("postcode", "verplicht");
		}
		if(! BestelBon.isPostcodeValid(postcode)){
			fouten.put("postcode", "postcode moet bestaan uit 4 cijfers");
		}
		String gemeente= request.getParameter("gemeente");
		if(! BestelBon.isStringValid(gemeente)){
			fouten.put("gemeente", "gemeente verplicht");
		}
		if(! BestelBon.isGemeenteValid(gemeente) && gemeente.length()>2){
			fouten.put("gemeente", "gelieve een echte gemeente in te vullen");
		}
		String afhalenopsturen= request.getParameter("bestelwijze");
		int afhalenopsturenI=2;
		if(afhalenopsturen==null) fouten.put("bestelwijze","maak een keuze");
		else{
			switch(afhalenopsturen){
			case "A":
				afhalenopsturenI=0;
				break;
			case "O":
				afhalenopsturenI=1;
				break;
			default:
				fouten.put("bestelwijze", "maak een keuze");
			}
		}
		if(fouten.isEmpty()){
			Date date= new Date();
			BestelBon bestelbon= new BestelBon(date, naam, straat, huisNr, postcode, gemeente, afhalenopsturenI);
			bestelbonservice.create(bestelbon);
			long bonid= bestelbon.getId();
			Set set= winkelmandje.entrySet();
			Iterator i= set.iterator();
			while(i.hasNext()){
				Map.Entry me= (Map.Entry)i.next();
				Long idwijn= (Long) me.getKey();
				int hoeveel= (int) me.getValue();
				BestelBonLijnPK bestelbonlijpk=new BestelBonLijnPK(bestelbon.getId(),idwijn);
				BestelBonLijn bestelbonlijn= new BestelBonLijn(bestelbon, wijnservice.read(idwijn), hoeveel);
				bestelbonlijn.setBestelbonlijnPK(bestelbonlijpk);
				System.out.println("Bestelbonlijn "+bestelbonlijn.getWijn().getId()+" "+bestelbonlijn.getBestelbon().getId()+ " "+hoeveel+ " "+bestelbonlijn.getBestelbonlijnPK().getBestelbonId());
				bestelbonlijnservice.create(bestelbonlijn);
			}
			//mandje leegmaken
			winkelmandje.clear();
			request.getSession().setAttribute("bestelbon", bestelbon.getId());
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL4, request.getContextPath())));
		}
		else{
			request.setAttribute("tijdelijkebestelling", winkelmandAG);
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
