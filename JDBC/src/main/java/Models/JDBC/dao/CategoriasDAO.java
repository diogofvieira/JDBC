package Models.JDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Models.JDBC.domains.Categoria;
import Models.JDBC.domains.Produto;

public class CategoriasDAO {
	
	private final Connection conn;

	public CategoriasDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Categoria> list() {
		
		
		List<Categoria> list = new ArrayList<Categoria>();
		String sql = "select * from Categoria";
		
		try {
			PreparedStatement smt = conn.prepareStatement(sql);
			smt.execute();
			ResultSet rs = smt.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				Categoria categoria = new Categoria(id, nome);
				list.add(categoria);
			}
			rs.close();
			smt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<Categoria> listWithProduto() {
		List<Categoria> list = new ArrayList<Categoria>();
		Categoria last = null;
		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from Categoria as c join Produto as p on p.categoria_id = c.id";
		
		try {
			PreparedStatement smt = conn.prepareStatement(sql);
			smt.execute();
			ResultSet rs = smt.getResultSet();
			while(rs.next()){
				int id = rs.getInt("c_id");
				String nome = rs.getString("c_nome");
				if(last==null || !last.getNome().equals(nome)){
					Categoria categoria = new Categoria(id, nome);
					list.add(categoria);
					last = categoria;
				}
				int idProduto = rs.getInt("p_id");
				String nomeProduto = rs.getString("p_nome");
				String descricaoProduto = rs.getString("p_descricao");
				
				Produto produto = new Produto(nomeProduto, descricaoProduto);
				produto.setId(idProduto);
				last.add(produto);
			}
			rs.close();
			smt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	

}
