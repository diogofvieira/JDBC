package Models.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestList {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionPool dataBase = new ConnectionPool();
		
		Connection connection = dataBase.getConnection();
		
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from produto");
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()){
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");
		
			System.out.println("\n" + id + "\n" + nome + "\n"+ descricao + "\n");
		}
		System.out.println(resultado);
		statement.close();
		resultSet.close();
		connection.close();
	}

	
}
