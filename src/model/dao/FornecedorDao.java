package model.dao;

import java.util.List;

import model.entities.Fornecedor;
import model.entities.Item;

public interface FornecedorDao {
	
	void insert(Fornecedor obj);
	void update(Fornecedor obj);
	void deleteByid(Integer id);
	Item findById(Integer id);
	List<Fornecedor> findAll();

}
