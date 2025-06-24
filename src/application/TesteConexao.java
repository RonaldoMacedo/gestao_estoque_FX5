package application;

import java.sql.Date;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.ProductDao;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;

public class TesteConexao {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ProductDao productDao = DaoFactory.createProductDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		
		/* System.out.println("--------Test 1: buscar produto por codigo----------");
		Product product = productDao.findById(7);
		System.out.println(product);
		
		System.out.println();
		
		System.out.println("--------Test 2: buscar todos os produtos----------");
		List<Product> list = productDao.findAll();
		for(Product obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();
		
		System.out.println("--------Test 3: buscar item por codigo----------");
		Item item = itemDao.findById(10);
		System.out.println(item);
		
		System.out.println();
		
		System.out.println("--------Test 4: buscar item por produto----------");
		Product prod = new Product(7, null, null, null, null, null, item);
		List<Item> list2 = itemDao.findByProduct(prod);
		for(Item obj: list2) {
			System.out.println(obj);
		}
		
		System.out.println();
		
		System.out.println("--------Test 5: buscar todos os itens----------");
		list2 = itemDao.findAll();
		for(Item obj: list2) {
			System.out.println(obj);
		} */
		
		System.out.println();
		
		System.out.println("--------Test 8: Deletando um produto----------");
		System.out.println("Digite um código de produto para deletar:");
		int id = sc.nextInt();
		productDao.deleteByid(id);
		System.out.println("Delete completed!");
		
		sc.close();
	}

}
