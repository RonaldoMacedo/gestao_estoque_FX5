package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class TesteConexao {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			int rows1 = st.executeUpdate("update produto set situacao = 'inativo' where id_produto = 182");
			
			int x = 1;
			if(x < 2) {
				throw new SQLException("Fake error");
			}
			
			int rows2 = st.executeUpdate("update produto set situacao = 'ativooo' where id_produto = 183");
			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
