package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.entities.Item;
import model.entities.Marca;

public class ItemService {
	
	private ItemDao dao = DaoFactory.createItemDao();
	
	public List<Item> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Item obj) {
		if(obj.getIdItem() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}

}
