package Models.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Models.JDBC.dao.ProdutoDAO;
import Models.JDBC.domains.Produto;

public class TestProdutoDAO {
	
	public static void main(String[] args) throws SQLException {
		
		Produto desk = new Produto("Desk", "Desk with 4 foots");
		
		try {
			ConnectionPool database = new ConnectionPool();
			Connection connection = database.getConnection();
			
			
			ProdutoDAO dao= new ProdutoDAO(connection);
			
			dao.save(desk);
			System.out.println(desk);
			
			List<Produto> list = dao.list();
			for (Produto produto : list) {
				System.out.println(produto);
			}
			
			dao.delete(desk.getId());
			
			connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
		
}
