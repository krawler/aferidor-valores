package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexaofirebird implements IConnectionFactory {
	
	public static final String DRIVER = "org.firebirdsql.jdbc.FBDriver";
	public static final String JDBC_URL = "jdbc:firebirdsql://127.0.0.1:3050/C:\\FBXTI\\FuturaData\\ERPFUTURA.FDB";
	public static final String USER = "ABLFELUS";
	public static final String PASS = "_$eliade@$_";		
	Connection conn = null;

	@Override
	public Connection criarConexao() throws Exception {
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(JDBC_URL,USER,PASS);
		}catch(SQLException e) {
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
