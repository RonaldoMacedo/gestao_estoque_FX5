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
import model.dao.FornecedorDao;
import model.entities.Fornecedor;
import model.entities.Item;
import model.enums.Situacao;

public class FornecedorDaoJDBC implements FornecedorDao {
	
	private Connection conn;
	
	public FornecedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Fornecedor obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into fornecedor(razao_social, apelido, cnpj, data_cadastro, situacao)\r\n"
					+ "	values(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getRazaoSocial());
			ps.setString(2, obj.getApelido());
			ps.setString(3, obj.getCnpj());
			ps.setDate(4, (Date) new java.sql.Date(obj.getDataCadastro().getTime()));
			ps.setString(5, obj.getSituacao().toString());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdFornecedor(id);
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
	public void update(Fornecedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fornecedor> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from fornecedor");
			rs = ps.executeQuery();
			
			List<Fornecedor> list = new ArrayList<>();
			Map<Integer, Fornecedor> map = new HashMap<>();
			
			while (rs.next()) {
				Fornecedor forn = map.get(rs.getInt("id_fornecedor"));
				if(forn == null) {
					forn = instantiateFornecedor(rs);
					map.put(rs.getInt("id_fornecedor"), forn);
				}
				Fornecedor obj = instantiateFornecedor(rs);
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

	private Fornecedor instantiateFornecedor(ResultSet rs) throws SQLException {
		Fornecedor forn = new Fornecedor();
		forn.setIdFornecedor(rs.getInt("id_fornecedor"));
		forn.setRazaoSocial(rs.getString("razao_social"));
		forn.setApelido(rs.getString("apelido"));
		forn.setCnpj(rs.getString("cnpj"));
		forn.setDataCadastro(rs.getDate("data_cadastro"));
		forn.setSituacao(Situacao.valueOf(rs.getString("situacao")));
		return forn;
	}

}
