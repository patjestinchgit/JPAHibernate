package be.toysforboys.DAO;

import be.toysforboys.entities.Product;

public class ProductDAO extends AbstractDAO{

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public void adaptStock(Product product) {
		// TODO Auto-generated method stub
		getEntityManager().merge(product);
		//getEntityManager().(product);
	}

}
