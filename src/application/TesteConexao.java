package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class TesteConexao {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProductDao();
		
		System.out.println("--------Test 1: buscar produto por codigo----------");
		Product product = productDao.findById(87);
		System.out.println(product);

	}

}
