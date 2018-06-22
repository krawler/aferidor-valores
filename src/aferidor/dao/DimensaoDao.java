package aferidor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aferidor.model.Consulta;
import aferidor.model.Dimensao;
import factory.ConexaoDerby;

public class DimensaoDao implements Dao<Dimensao> {
	
	private ConexaoDerby con = new ConexaoDerby();

	@Override
	public Dimensao salvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimensao editar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimensao excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimensao obterObjeto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dimensao> obterTodos() {
		
		ArrayList<Dimensao> listDimensoes = new ArrayList<Dimensao>();
		
		try {
			Statement stmt = con.criarConexao().createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM AFERIDOR.DIMENSAO");
			
			while(results.next()) {
				Dimensao consulta = new Dimensao(Integer.valueOf(results.getInt("ID")), 
												 results.getString("NOME"), 
												 results.getString("NOME_TABELA"));
				listDimensoes.add(consulta);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return listDimensoes;
	}

	@Override
	public List<String> listarNomesCombo() {
	    ArrayList<Dimensao> dimensoes = new ArrayList<Dimensao>();	    
	    ArrayList<String> nomes = new ArrayList<String>();
	    for (Dimensao dimensao : dimensoes) {
	    	 nomes.add(dimensao.getNome());
		}	    
		return nomes;
	}

	@Override
	public List<String> listarNomesComboByFilterField(String campo, Integer id) {
		
		ArrayList<Dimensao> listDimensoes = new ArrayList<Dimensao>();
		
		try {			
			PreparedStatement pstmt = con.criarConexao().prepareStatement("SELECT * FROM AFERIDOR.DIMENSAO WHERE "+ campo +" = ?");
			//pstmt.setString(1, campo);
			pstmt.setInt(1, id);
			ResultSet results = pstmt.executeQuery();
			
			while(results.next()) {
				Dimensao consulta = new Dimensao(Integer.valueOf(results.getInt("ID")), 
												 results.getString("NOME"), 
												 results.getString("NOME_TABELA"));
				listDimensoes.add(consulta);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
			    
	    ArrayList<String> nomes = new ArrayList<String>();
	    for (Dimensao dimensao : listDimensoes) {
	    	 nomes.add(dimensao.getNome());
		}	    
		return nomes;
	}

}
