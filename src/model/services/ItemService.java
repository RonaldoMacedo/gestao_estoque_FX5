package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.entities.Item;

public class ItemService {
	
	private ItemDao dao = DaoFactory.createItemDao();
	
	public List<Item> findAll() {
		return dao.findAll();
	}

}
