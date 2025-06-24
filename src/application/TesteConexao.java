package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class TesteConexao {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProductDao();
		
		Product product = productDao.findById(1);
		
		System.out.println(product);

	}

}
