package aferidor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import aferidor.model.CampoFiltro;
import factory.ConexaoDerby;

public class CampoFiltroDao implements Dao<CampoFiltro> {
	
	private ConexaoDerby con = new ConexaoDerby();

	@Override
	public CampoFiltro salvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CampoFiltro editar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CampoFiltro excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CampoFiltro obterObjeto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CampoFiltro> obterTodos() {
		
		List<CampoFiltro> camposFiltros = new ArrayList<CampoFiltro>();
		try {
			Statement stmt = con.criarConexao().createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM AFERIDOR.CAMPOS_FILTRO");
			while(results.next()) {
				  CampoFiltro cf = new CampoFiltro(results.getInt("ID"), results.getString("NOME"), results.getString("CAMPO_DW"), 
						  						   results.getString("CAMPO_TRANS"), results.getInt("ID_CONSULTA"), 
						  						   results.getInt("ID_CUBO"), results.getInt("ID_DIMENSAO"));
				  camposFiltros.add(cf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return camposFiltros;
	}

	@Override
	public List<String> listarNomesCombo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listarNomesComboByFilterField(String campo, Integer id) {
		
		ArrayList<CampoFiltro> listCamposFiltros = new ArrayList<CampoFiltro>();
		
		try {
			PreparedStatement pstmt = con.criarConexao()
										.prepareStatement("SELECT * FROM AFERIDOR.CAMPOS_FILTRO WHERE " + campo + " = ?");
			pstmt.setInt(1, id);
			ResultSet results = pstmt.executeQuery();
			
			while(results.next()) {
				CampoFiltro campoFiltro = new CampoFiltro(Integer.valueOf(results.getInt("ID")), results.getString("NOME"), 
													results.getString("CAMPO_DW"), results.getString("CAMPO_TRANS"), results.getInt("ID_CONSULTA"), 
													results.getInt("ID_CUBO"), results.getInt("ID_DIMENSAO"));
				listCamposFiltros.add(campoFiltro);
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<String> nomes = new ArrayList<String>();
		for (CampoFiltro filtro : listCamposFiltros) {
			nomes.add(filtro.getNome());
		}
		
		return nomes;
	}

}
