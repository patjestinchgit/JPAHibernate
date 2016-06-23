package be.toysforboys.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.type.SortedSetType;

import be.toysforboys.entities.Countrie;
import be.toysforboys.entities.Customer;
import be.toysforboys.entities.Order;
import be.toysforboys.entities.OrderDetails;
import be.toysforboys.entities.Product;
import be.toysforboys.services.CountrieService;
import be.toysforboys.services.CustomerService;
import be.toysforboys.services.OrdersService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	public static final String REDIRECT_URL1= "%s/orders/order.htm?id=%d";
	private transient OrdersService ordersservice= new OrdersService();
	private transient CountrieService countrieservice= new CountrieService();
	private transient CustomerService customerservice= new CustomerService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	    request.setAttribute("bestellingen", ordersservice.findAll());
	    String orderid= (request.getParameter("orderid"));
	    if(orderid!=null){
	    	long orderidl= Long.parseLong(orderid);
	    	request.getSession().setAttribute("orderid", orderidl);
	    	response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL1, request.getContextPath(),orderidl)));
	    }
	    //Andere optie geven als er shipped word geklikked wordt
	    else request.getRequestDispatcher(VIEW).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] listshipped= request.getParameterValues("setttingasshipped");
		LinkedList<Order> listorders= new LinkedList<Order>();
		LinkedList<Product> listproducts= new LinkedList<Product>();
		Map<String, String> fouten = new HashMap();
		System.out.println("listshipped "+listshipped.length+listshipped[0]);
		if(listshipped!=null){
			System.out.println("kom ik erin");
			for(int i=0; i<listshipped.length; i++){
				System.out.println("ttt" + listshipped[i]);
				listorders.add(ordersservice.read(Long.parseLong(listshipped[i])));
			}
			for(int i=0; i<listorders.size();i++){
				//boolean return
				//if boolean false
				if(checkQuantity(listorders.get(i))==false){
					fouten.put(Long.toString(listorders.get(i).getId()),"not enough stock");
				}
			}
			
		}
		if(fouten.isEmpty()){
			//Alles aanpassen
			DateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
			Date shippeddate= new Date();
			String dateformatS= dateformat.format(shippeddate);
			ordersservice.addaptToShipping(listorders, shippeddate);
			//productservice.addaptStock()
			request.setAttribute("bestellingen", ordersservice.findAll());
			request.getRequestDispatcher(VIEW).forward(request, response);
			
		}
		else{
			request.setAttribute("bestellingen", listorders);
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}			
	}

	private boolean checkQuantity(Order order) {
		// TODO Auto-generated method stub
		Set<OrderDetails> listorderdetails= order.getOrderDetails();
		Iterator<OrderDetails> iterator= listorderdetails.iterator();
		boolean returnboolean= true;
		while(iterator.hasNext()) {
			OrderDetails orderdetails= iterator.next();
			if((orderdetails.getQuantityordered()>orderdetails.getProduct().getQuantityinorder())||((orderdetails.getQuantityordered()>orderdetails.getProduct().getQuantityinstock()))){
				returnboolean= false;
			}
		}
		
		return returnboolean;
	}
	
	
	
}
