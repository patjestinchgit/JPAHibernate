package be.toysforboys.services;

import be.toysforboys.DAO.ProductDAO;
import be.toysforboys.entities.Product;

public class ProductServices {
	public transient ProductDAO productDAO= new ProductDAO();
	public ProductServices() {
		// TODO Auto-generated constructor stub
	}
	
	public void adaptQuantity(Product product, int quantity){
		product.setQuantityinorder(product.getQuantityinorder()-quantity);
		product.setQuantityinstock(product.getQuantityinstock()-quantity);
		productDAO.beginTransaction();
		productDAO.adaptStock(product);
		productDAO.commit();
	}

}
