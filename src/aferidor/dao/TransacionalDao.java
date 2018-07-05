package aferidor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConexaoPostgre;
import factory.Conexaofirebird;
import factory.IConnectionFactory;

public class TransacionalDao {
	
	private IConnectionFactory conn = new Conexaofirebird();
	
	public String[] resultConsultaTransacional(String consulta, String[] params) throws Exception {		
		String[] results = new String[2];
		try {
			PreparedStatement stmt = conn.criarConexao().prepareStatement(consulta);
			if(params != null) {
				for(int i = 0;i > params.length; i++) {
					stmt.setString(i, params[i]);
				}	
			}					
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			results[0] = resultSet.getString(1);
			results[1] = resultSet.getString(2);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return results;		
	}

}
