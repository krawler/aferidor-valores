package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoPostgre implements IConnectionFactory {
	
	public static final String DRIVER = "org.postgresql.Driver";
	public static final String JDBC_URL = "jdbc:postgresql://192.168.100.51:5433/DW_ATC";
	public static final String USER = "postgres";
	public static final String PASS = "postgres";		
	Connection conn = null;

	@Override
	public Connection criarConexao() throws Exception {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,USER,PASS);			
		} catch (SQLException e) {			 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs) {
		try {
			
			if(conexao!=null){
				conexao.close(); 
			}
			
			if(pstmt!=null){
				pstmt.close(); 
			}
			
			if(rs!=null){
			   rs.close(); 
			}
			
		} catch (Exception e) {
			System.out.println();
		}		
	}

}
