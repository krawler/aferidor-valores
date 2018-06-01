package aferidor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aferidor.model.Consulta;

public class ConsultaDao implements Dao<Consulta> {
	
	private Conexao con = new Conexao();

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
			Statement stmt = con.obterConexao().createStatement();
			ResultSet results = stmt.executeQuery("select * from AFERIDOR.CONSULTAS");
			
			while(results.next()) {
				Consulta consulta = new Consulta(Integer.valueOf(results.getInt("ID")), 
												 results.getString("SQL_CONSULTA"), 
												 results.getString("TIPO_CONSULTA"));
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}

}
