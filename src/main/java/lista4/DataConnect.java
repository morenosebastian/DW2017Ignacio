package lista4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {

	public DataConnect() {
		super();
	}

	public static Connection obterConexao() {
		String nomeEsquema = "dbvilmar";
		String enderecoBanco = "jdbc://mysql://localhost/" + nomeEsquema;
		String usuario = "root";
		String senha = "root";
		String driverJDBC = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			System.out.println("Conexão aberta");
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o banco");
			throw new RuntimeException(e);
		}
	}

	public static void fecharConexao(Connection con) {
		try {
			con.close();
			System.out.println("Conexão fechada");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}