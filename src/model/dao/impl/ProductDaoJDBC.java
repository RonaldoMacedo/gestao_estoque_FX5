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
import model.dao.ProductDao;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;

public class ProductDaoJDBC implements ProductDao {
	
	private Connection conn;
	
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from produto where id_produto = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Product obj = instantiateProduct(rs);
				return obj;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Product instantiateProduct(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setIdProduto(rs.getInt("id_produto"));
		obj.setDescricaoInterna(rs.getString("descricao_interna"));
		obj.setDataCadastro(rs.getDate("data_cadastro"));
		obj.setGrupo(Grupo.valueOf(rs.getString("grupo")));
		obj.setSituacao(Situacao.valueOf(rs.getString("situacao")));
		obj.setSaldo(rs.getInt("saldo"));
		return obj;
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from produto");
			rs = ps.executeQuery();
			List<Product> list = new ArrayList<>();
			Map<Integer, Product> map = new HashMap<>();
			
			while(rs.next()) {
				Product obj = map.get(rs.getInt("id_produto"));
				
				if(obj == null) {
					obj = instantiateProduct(rs);
					map.put(rs.getInt("id_produto"), obj);
				}
				
				obj = instantiateProduct(rs);
				list.add(obj);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
