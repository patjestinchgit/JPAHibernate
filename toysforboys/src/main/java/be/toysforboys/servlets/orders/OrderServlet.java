package be.toysforboys.servlets.orders;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.toysforboys.entities.Order;
import be.toysforboys.entities.OrderDetails;
import be.toysforboys.services.CountrieService;
import be.toysforboys.services.CustomerService;
import be.toysforboys.services.OrderdetailsService;
import be.toysforboys.services.OrdersService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/orders/order.htm")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/order.jsp";
	public static final String REDIRECT_URL= "%s/index.htm";
	private transient OrdersService ordersservice= new OrdersService();
	private transient OrderdetailsService orderdetailsservice= new OrderdetailsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Object orderido= request.getSession().getAttribute("orderid");
		String TNOZ=request.getParameter("terugnaaroverzicht");
		long orderidl= (long) orderido;
		List<OrderDetails> lijstordetails= orderdetailsservice.findByOrder(orderidl);
		Order order= ordersservice.read(orderidl);
		Set<OrderDetails> setlijstordetails= order.getOrderdetails();
		
		Iterator<OrderDetails> iterator= setlijstordetails.iterator();
		while(iterator.hasNext()) {
			OrderDetails orderdetails= iterator.next();
			System.out.println(orderdetails.toString()+" "+orderdetails.getOrder().getId()+" "+orderdetails.getProduct().toString());
		}
		
		if(TNOZ!=null){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
		else{
			request.setAttribute("orderdetails", setlijstordetails);
			request.setAttribute("order", ordersservice.read(orderidl));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
