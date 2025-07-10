package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Marca;

public class MarcaService {
	
	//MOCK de dados
	public List<Marca> findAll() {
		List<Marca> list = new ArrayList<>();
		list.add(new Marca(1, "Descarpack"));
		list.add(new Marca(2, "Greiner"));
		list.add(new Marca(3, "Medix"));
		list.add(new Marca(4, "Firstlab"));
		return list;
	}

}
