package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.entities.Fornecedor;

public class FornecedorService {
	
	private FornecedorDao dao = DaoFactory.createFornecedorDao();
	
	// MOCK
	public List<Fornecedor> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Fornecedor obj) {
		if(obj.getIdFornecedor() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}

}
