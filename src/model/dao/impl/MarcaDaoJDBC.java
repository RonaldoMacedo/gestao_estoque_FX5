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
import model.dao.MarcaDao;
import model.entities.Marca;

public class MarcaDaoJDBC implements MarcaDao {
	
	private Connection conn;
	
	public MarcaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Marca obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Marca obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Marca findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Marca> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from marca");
			rs = ps.executeQuery();
			List<Marca> list = new ArrayList<>();
			Map<Integer, Marca> map = new HashMap<>();
			
			while(rs.next()) {
				Marca obj = map.get(rs.getInt("id_marca"));
				
				if(obj == null) {
					obj = instantiateMarca(rs);
					map.put(rs.getInt("id_marca"), obj);
				}
				
				obj = instantiateMarca(rs);
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

	private Marca instantiateMarca(ResultSet rs) throws SQLException {
		Marca obj = new Marca();
		obj.setIdMarca(rs.getInt("id_marca"));
		obj.setMarca(rs.getString("nome_fantasia"));
		return obj;
	}

}
