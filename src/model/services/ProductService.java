package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductService {
	
	private ProductDao dao = DaoFactory.createProductDao();
	
	// Mock de dados
	public List<Product> findAll(){
		return dao.findAll();
	}

}
