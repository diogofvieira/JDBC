package Models.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Models.JDBC.dao.CategoriasDAO;
import Models.JDBC.domains.Categoria;
import Models.JDBC.domains.Produto;

public class TestaCategorias {
	
	public static void main(String[] args) throws SQLException{
		try {
			Connection conn = new ConnectionPool().getConnection();
			List<Categoria> categorias = new CategoriasDAO(conn).listWithProduto();
			for (Categoria categoria : categorias) {
				System.out.println(categoria.getNome());
				
				for(Produto produto : categoria.getListProduto()){
					System.out.println(categoria.getNome() + " - " + produto.getNome());
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
