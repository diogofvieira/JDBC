package Models.JDBC.domains;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	private Integer id;
	private String nome;
	private List<Produto> listProduto = new ArrayList<Produto>();
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
	
	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void add(Produto produto) {
		this.listProduto.add(produto);
		
	}
	
	

}
