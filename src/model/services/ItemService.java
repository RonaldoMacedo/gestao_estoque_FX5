package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entities.Item;
import model.enums.Situacao;

public class ItemService {
	
	public List<Item> findAll() {
		//MOCK
		List<Item> list = new ArrayList<>();
		list.add(new Item(1, "Luva de latex pp sem po - 100un", new Date(), null, "7891354081000", Situacao.Ativo, null, null));
		return list;
	}

}
