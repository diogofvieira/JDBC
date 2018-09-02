package Models.JDBC;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {
	
	private DataSource dataSource;
	
	ConnectionPool(){
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/testdb");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;
	}
	
	 Connection getConnection() throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
		 Connection connection = dataSource.getConnection();
		 return connection;
	}


}
