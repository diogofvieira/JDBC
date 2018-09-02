package Models.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {

	
	public static void main(String[] args) throws SQLException {
		
		ConnectionPool dataBase = new ConnectionPool();
		
		Connection connection = dataBase.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("delete from produto where id > 3");
		int count = statement.getUpdateCount();
		System.out.println(count + " refreshed lines");
		statement.close();
		connection.close();
	}

}
