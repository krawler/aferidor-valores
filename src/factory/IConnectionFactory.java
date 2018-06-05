package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IConnectionFactory {
	
	public Connection criarConexao() throws Exception;
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs);

}