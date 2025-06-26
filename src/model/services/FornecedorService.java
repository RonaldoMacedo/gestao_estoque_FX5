package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Fornecedor;
import model.enums.Situacao;

public class FornecedorService {
	
	// MOCK
	public List<Fornecedor> findAll() {
		List<Fornecedor> list = new ArrayList<>();
		list.add(new Fornecedor(1, "Aimara Comercio e Representacoes Ltda", "Aimara", "57.202.418/0001-07", null, Situacao.Ativo));
		return list;
	}

}
