package model.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;

public class ProductService {
	
	// Mock de dados
	public List<Product> findAll(){
		List<Product> list = new ArrayList<>();
		list.add(new Product(1, "Luva", new Date(11111), Grupo.Coleta, Situacao.Ativo, null, null));
		return list;
	}

}
