package Models.JDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.JDBC.domains.Categoria;
import Models.JDBC.domains.Produto;

public class ProdutoDAO {
	
	private final Connection conn;
	
	public ProdutoDAO(Connection conn){
		this.conn = conn;
	}
	
	//Insert
	public void save(Produto desk)throws SQLException {
		try {
			String sql = "insert into Produto (nome, descricao) values(?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);

			add(desk, statement);

			conn.commit();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}
	

	
	//Select produto
	public List<Produto> list() {
		List<Produto> list = new ArrayList<Produto>();
		String sql = "select * from produto";
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.execute();
			ResultSet rs = transformResultAsProduto(list, stm);
			stm.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Delete
	public void delete(int id) throws SQLException{
		try{
			String sql = "delete from produto where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			int count = statement.getUpdateCount();
			System.out.println(count + "refreshed lines");
			
			statement.close();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void add(Produto produto, PreparedStatement statement) throws SQLException {
		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		
		boolean resultado = statement.execute();
		System.out.println(resultado);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		while(resultSet.next()){
			Integer id = resultSet.getInt("id");
			System.out.println(id + "New Id");
			produto.setId(id);
		}
		resultSet.close();
	}

	//Select produto
	public List<Produto> list(Categoria categoria) {
		List<Produto> list = new ArrayList<Produto>();
		String sql = "select * from produto where categoria_id = ?";
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, categoria.getId());
			stm.execute();
			
			ResultSet rs = transformResultAsProduto(list, stm);
			
			stm.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private ResultSet transformResultAsProduto(List<Produto> list, PreparedStatement stm)throws SQLException {
		ResultSet rs = stm.getResultSet();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				list.add(produto);
			}
			
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
