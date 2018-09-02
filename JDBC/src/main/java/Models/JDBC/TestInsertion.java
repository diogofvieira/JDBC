package Models.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertion {
	
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionPool dataBase = new ConnectionPool();
		
		Connection connection = dataBase.getConnection();
		connection.setAutoCommit(false);
		
		try {
		String sql = "insert into Produto (nome, descricao) values(?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);

			add("Notebook'15", "Notebook i5", statement);
			add("Notebook'i7", "Notebook i7", statement);
			connection.commit();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			connection.close();
		}
		
		connection.close();
		
	}

	private static void add(String nome, String desc, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, desc);
		
		boolean resultado = statement.execute();
		System.out.println(resultado);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		while(resultSet.next()){
			String id = resultSet.getString("id");
			System.out.println(id + "New Id");
		}
		resultSet.close();
	}

}
