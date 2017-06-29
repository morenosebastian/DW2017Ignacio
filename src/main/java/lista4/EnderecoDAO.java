package lista4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

	private Connection conexao;

	public static void main(String[] a) {
		

		EnderecoDAO dao = new EnderecoDAO();

		Endereco endereco = new Endereco();
		endereco.setBairro("Coqueiros");
		endereco.setRua("Outra rua");
		endereco.setNumero(150);

		dao.incluir(endereco);
		dao.listar();

	}

	public EnderecoDAO() {
		this.getConexao();
	}

	private void getConexao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}

	public int incluir(Endereco endereco) {
		this.getConexao();
		int idInserido = 0;
		String sql = "INSERT INTO Endereco (rua, numero, bairro) VALUES (?, ?, ?)";

		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());

			// executa
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Endereço inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir endereço");
			}

			stmt.close();

			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço no BD!");
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean atualizar(Endereco endereco) {
		this.getConexao();
		boolean atualizadoSucesso = false;
		String sql = "UPDATE Endereco SET rua = ?, numero = ?, bairro = ? WHERE idENDERECO = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setInt(4, endereco.getId());
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Endereço atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar endereço");
			}

			stmt.close();

			return atualizadoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar endereço");
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean remover(int cdPessoa) {
		this.getConexao();
		boolean removidoSucesso = false;
		String sql = "DELETE FROM Endereco WHERE idENDERECO IN (SELECT p.idendereco from Pessoa p WHERE p.idPESSOA = ?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cdPessoa);

			int codigoRetorno = stmt.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Endereço removido com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover endereço");
			}
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erro ao remover endereço");
		}
		return removidoSucesso;
	}

	public Endereco obter(int cdPessoa) {
		this.getConexao();
		Endereco endereco = null;
		String sql = "SELECT e.* FROM Endereco e INNER JOIN Pessoa p ON e.idENDERECO = p.idendereco WHERE p.idPESSOA = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cdPessoa);
			stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				endereco = this.obterEnderecoResultSet(rs);
			}
			stmt.close();
			return endereco;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereco no BD!");
			return null;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	private Endereco obterEnderecoResultSet(ResultSet rs) {
		Endereco endereco = null;
		try {
			endereco = new Endereco();
			endereco.setId(rs.getInt("idENDERECO"));
			endereco.setRua(rs.getString("rua"));
			endereco.setNumero(rs.getInt("numero"));
			endereco.setBairro(rs.getString("bairro"));

			System.out.println(endereco.toString());
		} catch (SQLException e) {
			System.out.println("Erro ao obter endereço");
		}
		return endereco;
	}

	public List<Endereco> listar() {
		this.getConexao();
		List<Endereco> enderecos = new ArrayList<Endereco>();
		String sql = "SELECT * FROM Endereco";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = this.obterEnderecoResultSet(rs);
				enderecos.add(endereco);
			}
			stmt.close();
			return enderecos;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereços");
			return enderecos;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}
}
