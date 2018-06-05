package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoDerby implements IConnectionFactory {
	
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:C:\\Users\\Rafael.Ramos\\Documents\\workspace-sts-3.9.1.RELEASE\\aferidor-valores\\db\\consultas;create=true";
	Connection conn = null;
	

	@Override
	public Connection criarConexao() throws Exception {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);			
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
