package be.muziek.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// enkele imports ...
@WebFilter("*.htm")
public class JPAFilter implements Filter {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("muziek"); 
	private static final ThreadLocal<EntityManager> entityManagers = new ThreadLocal<>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	/*@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		chain.doFilter(request, response);
	}*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException { 
		entityManagers.set(entityManagerFactory.createEntityManager()); 
		try {
			//System.out.println("lijn 35"+entityManagers.get().isOpen());
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		} finally {
			EntityManager entityManager = entityManagers.get(); 
			//entityManager.getTransaction().begin();
			System.out.println("lijn41 "+entityManager.getTransaction().isActive());
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback(); 
			}
			System.out.println("lijn45 jpafilter");
			entityManager.close(); 
			entityManagers.remove(); 
		}
	}
	public static EntityManager getEntityManager() {
		System.out.println("lijn50 jpafilter");
		return entityManagers.get(); 
	}
	@Override
	public void destroy() {
		entityManagerFactory.close();
	}
	/*public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}*/
}