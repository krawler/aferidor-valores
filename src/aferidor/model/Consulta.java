package aferidor.model;

public class Consulta {
	
	private Integer id;
	private String sqlConsulta;	
	private String tipo;
	private String sqlDW;
	private String nome;
	
	public Consulta(Integer id, String sqlConsulta, String tipo, String sqlDW, String nome) {
		super();
		this.id = id;
		this.sqlConsulta = sqlConsulta;
		this.tipo = tipo;
		this.sqlDW = sqlDW;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getSqlConsulta() {
		return sqlConsulta;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSqlConsulta(String sqlConsulta) {
		this.sqlConsulta = sqlConsulta;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public String getSqlDW() {
		return sqlDW;
	}

	public void setSqlDW(String sqlDW) {
		this.sqlDW = sqlDW;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {		
		return this.getId().toString() + " - " + this.getSqlConsulta() + " - " + this.getTipo();
	}

}
