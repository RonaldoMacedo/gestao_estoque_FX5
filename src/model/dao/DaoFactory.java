package model.dao;

import db.DB;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.ItemDaoJDBC;
import model.dao.impl.MarcaDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {
	
	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoJDBC(DB.getConnection());
	}
	
	public static FornecedorDao createFornecedorDao() {
		return new FornecedorDaoJDBC(DB.getConnection());
	}
	
	public static MarcaDao createMarcaDao() {
		return new MarcaDaoJDBC(DB.getConnection());
	}

}
