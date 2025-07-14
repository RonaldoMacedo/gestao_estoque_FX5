package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MarcaDao;
import model.entities.Marca;

public class MarcaService {
	
	private MarcaDao dao = DaoFactory.createMarcaDao();
	
	public List<Marca> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Marca obj) {
		if(obj.getIdMarca() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}

}
