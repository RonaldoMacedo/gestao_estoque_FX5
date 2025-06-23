package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class TesteConexao {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("update produto "
					+ "set situacao = ? "
					+ "where "
					+ "(id_produto = ?)");
			ps.setString(1, "ativo");
			ps.setInt(2, 184);
			
			int rowsAffected = ps.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

}
