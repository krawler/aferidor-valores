package aferidor.model;

public class CampoFiltro {
	
	private int id;
	private String nome;	
	private String campoDW;
	private String campoTransacional;
	private int idConsulta;
	private int idcubo;
	private int idDimensao;	
	
	public CampoFiltro(int id, String nome, String campoDW, String campoTransacional, int idConsulta, int idcubo,
			int idDimensao) {
		super();
		this.id = id;
		this.nome = nome;
		this.campoDW = campoDW;
		this.campoTransacional = campoTransacional;
		this.idConsulta = idConsulta;
		this.idcubo = idcubo;
		this.idDimensao = idDimensao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCampoDW() {
		return campoDW;
	}
	public void setCampoDW(String campoDW) {
		this.campoDW = campoDW;
	}
	public String getCampoTransacional() {
		return campoTransacional;
	}
	public void setCampoTransacional(String campoTransacional) {
		this.campoTransacional = campoTransacional;
	}
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public int getIdcubo() {
		return idcubo;
	}
	public void setIdcubo(int idcubo) {
		this.idcubo = idcubo;
	}
	public int getIdDimensao() {
		return idDimensao;
	}
	public void setIdDimensao(int idDimensao) {
		this.idDimensao = idDimensao;
	}
	

}
