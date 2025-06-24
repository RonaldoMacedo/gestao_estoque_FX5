package model.dao;

import db.DB;
import model.dao.impl.ItemDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {
	
	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoJDBC(DB.getConnection());
	}

}
