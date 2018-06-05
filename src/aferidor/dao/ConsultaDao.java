package aferidor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aferidor.model.Consulta;
import factory.ConexaoDerby;

public class ConsultaDao implements Dao<Consulta> {
	
	private ConexaoDerby con = new ConexaoDerby();

	@Override
	public Consulta salvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta editar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta obterObjeto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> obterTodos() {
		
		ArrayList<Consulta> listConsultas = new ArrayList<Consulta>();
		
		try {
			Statement stmt = con.criarConexao().createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM AFERIDOR.CONSULTAS");
			
			while(results.next()) {
				Consulta consulta = new Consulta(Integer.valueOf(results.getInt("ID")), 
												 results.getString("SQL_CONSULTA"), 
												 results.getString("TIPO_CONSULTA"));
				listConsultas.add(consulta);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return listConsultas;
	}

	@Override
	public List<String> listarNomesCombo() {
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		ArrayList<String> nomes = new ArrayList<String>();
		consultas = (ArrayList<Consulta>) this.obterTodos();
		for (Consulta c: consultas) {
			nomes.add(c.getSqlConsulta().substring(0, 25) + " - " + c.getTipo());
		}		
		return nomes;
	}

}
