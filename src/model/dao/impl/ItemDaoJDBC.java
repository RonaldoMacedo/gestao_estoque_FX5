package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ItemDao;
import model.entities.Item;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;

public class ItemDaoJDBC implements ItemDao {

	private Connection conn;

	public ItemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Item obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Item obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from item i inner join\r\n"
							+ "produto p on(i.fk_id_produto = p.id_produto)\r\n"
							+ "where id_item = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Product prod = instantiateProduct(rs);
				Item obj = instantiateItem(rs, prod);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Product instantiateProduct(ResultSet rs) throws SQLException {
		Product prod = new Product();
		prod.setIdProduto(rs.getInt("id_produto"));
		prod.setDescricaoInterna(rs.getString("descricao_interna"));
		prod.setDataCadastro(rs.getDate("data_cadastro"));
		prod.setGrupo(Grupo.valueOf(rs.getString("grupo")));
		prod.setSituacao(Situacao.valueOf(rs.getString("situacao")));
		prod.setSaldo(rs.getInt("saldo"));
		return prod;
	}

	private Item instantiateItem(ResultSet rs, Product prod) throws SQLException {
		Item obj = new Item();
		obj.setIdItem(rs.getInt("id_item"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setDataCadastro(new java.util.Date(rs.getTimestamp("data_cadastro").getTime()));
		obj.setCodigoRef(rs.getString("codigo_ref"));
		obj.setCodigoDeBarras(rs.getString("codigo_barras"));
		obj.setSituacao(Situacao.valueOf(rs.getString("situacao")));
		obj.setProduct(prod);
		obj.setSaldo(rs.getInt("saldo"));
		return obj;
	}

	@Override
	public List<Item> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from item i inner join\r\n"
							+ "produto p on(i.fk_id_produto = p.id_produto)\r\n");
			rs = ps.executeQuery();
			
			List<Item> list = new ArrayList<>();
			Map<Integer, Product> map = new HashMap<>();
			
			while (rs.next()) {
				Product prod = map.get(rs.getInt("id_produto"));
				if(prod == null) {
					prod = instantiateProduct(rs);
					map.put(rs.getInt("id_produto"), prod);
				}
				Item obj = instantiateItem(rs, prod);
				list.add(obj);
			}
			return list;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Item> findByProduct(Product product) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from item i inner join\r\n"
							+ "produto p on(i.fk_id_produto = p.id_produto)\r\n"
							+ "where id_produto = ?");
			ps.setInt(1, product.getIdProduto());
			rs = ps.executeQuery();
			
			List<Item> list = new ArrayList<>();
			Map<Integer, Product> map = new HashMap<>();
			
			while (rs.next()) {
				Product prod = map.get(rs.getInt("id_produto"));
				if(prod == null) {
					prod = instantiateProduct(rs);
					map.put(rs.getInt("id_produto"), prod);
				}
				Item obj = instantiateItem(rs, prod);
				list.add(obj);
			}
			return list;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
