	package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into produto(descricao_interna, data_cadastro, grupo, situacao)\r\n"
					+ "	values(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getDescricaoInterna());
			ps.setDate(2, (Date) new java.sql.Date(obj.getDataCadastro().getTime()));
			ps.setString(3, obj.getGrupo().toString());
			ps.setString(4, obj.getSituacao().toString());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdProduto(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected...");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Product obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("update produto "
					+ "set descricao_interna=?, data_cadastro=?, grupo=?, situacao=? "
					+ "where id_produto=?");
			ps.setString(1, obj.getDescricaoInterna());
			ps.setDate(2, (Date) new java.util.Date(obj.getDataCadastro().getTime()));
			ps.setString(3, obj.getGrupo().toString());
			ps.setString(4, obj.getSituacao().toString());
			ps.setInt(5, obj.getIdProduto());
			
			ps.executeUpdate();
				
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteByid(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete from produto where id_produto = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
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
