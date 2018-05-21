package aferidor.model;

public class Consulta {
	
	private Integer id;
	private String sqlConsulta;
	private String tipo;
	
	public Consulta(Integer id, String sqlConsulta, String tipo) {
		super();
		this.id = id;
		this.sqlConsulta = sqlConsulta;
		this.tipo = tipo;
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

}
