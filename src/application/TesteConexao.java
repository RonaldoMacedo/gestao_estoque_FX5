package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class TesteConexao {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("insert into produto " + "(descricao_interna, data_cadastro, grupo, situacao) " + "values " + "(?, ?, ?, ?)");
			ps.setString(1, "Agulha 25x7 normal");
			ps.setDate(2, new Date(sdf.parse("23/06/2025").getTime()));
			ps.setString(3, "Coleta");
			ps.setString(4, "Ativo");
			
			int rowsAffect = ps.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffect);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
			
		}

	}

}
