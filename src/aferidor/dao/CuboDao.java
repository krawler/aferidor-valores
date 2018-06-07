package aferidor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aferidor.model.Consulta;
import aferidor.model.Cubo;
import factory.ConexaoDerby;

public class CuboDao implements Dao<Cubo> {
	
	private ConexaoDerby con = new ConexaoDerby();

	@Override
	public Cubo salvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cubo editar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cubo excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cubo obterObjeto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cubo> obterTodos() {
		
		ArrayList<Cubo> listCubos = new ArrayList<Cubo>();
		
		try {
			Statement stmt = con.criarConexao().createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM AFERIDOR.CUBO");
			
			while(results.next()) {
				Cubo cubo = new Cubo(Integer.valueOf(results.getInt("ID")), 
												 results.getString("NOME"));
				listCubos.add(cubo);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return listCubos;
	}

	@Override
	public List<String> listarNomesCombo() {
		ArrayList<Cubo> cubos = new ArrayList<Cubo>();
		ArrayList<String> nomes = new ArrayList<String>();
		cubos = (ArrayList<Cubo>) this.obterTodos();
		for (Cubo c: cubos) {
			nomes.add(c.getNome());
		}		
		return nomes;
	}

	@Override
	public List<String> listarNomesComboByFilterField(String campo, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
