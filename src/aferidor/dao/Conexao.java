package aferidor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:C:\\Users\\Rafael.Ramos\\Documents\\workspace-sts-3.9.1.RELEASE\\aferidor-valores\\db\\consultas;create=true";
	Connection conn = null;
	
	public Connection obterConexao() {
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
	

}
