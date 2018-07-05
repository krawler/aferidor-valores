package aferidor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConexaoPostgre;
import factory.IConnectionFactory;

public class DWDao {
	
	private ConexaoPostgre con = new ConexaoPostgre();
	
	public List<String> listaCamposTabela(String tabela) throws SQLException, Exception{
		
		ArrayList<String> listRetorno = new ArrayList<String>();		
		PreparedStatement pstmt = con.criarConexao().prepareStatement("select * from :tabela");
		pstmt.setString(0, tabela);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmt = rs.getMetaData();
		
		for(int i = 1; i <= rsmt.getColumnCount();i++) {
			listRetorno.add(rsmt.getColumnName(i));
		}
		
		return listRetorno;		
	}
	
	public String[] resultConsultaDW(String consulta, String[] params) throws Exception {
		IConnectionFactory conn = new ConexaoPostgre();
		String[] results = new String[3];
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
