package model.dao;

import java.util.List;

import model.entities.Item;

public interface ItemDao {
	
	void insert(Item obj);
	void update(Item obj);
	void deleteByid(Integer id);
	Item findById(Integer id);
	List<Item> findAll();

}
