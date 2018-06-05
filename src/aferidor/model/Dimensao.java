package aferidor.model;

public class Dimensao {
	
	private Integer id;
	private String nome;
	private String nomeTabela;	
	
	public Dimensao(Integer id, String nome, String nomeTabela) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeTabela = nomeTabela;
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
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}	

}
