package lista4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

	private Connection conexao;

	public PessoaDAO() {
		this.getConexao();
	}

	private void getConexao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}

	public int incluir(Pessoa pessoa) {
		this.getConexao();
		int idInserido = 0;
		int idEndereco = 0;
		String sql = "INSERT INTO Pessoa (nome, sobrenome, idENDERECO) VALUES (?, ?, ?)";

		if (pessoa.getEndereco() != null) {
			Endereco endereco = pessoa.getEndereco();
			EnderecoDAO enderecoDAO = new EnderecoDAO();

			idEndereco = enderecoDAO.incluir(endereco);
			if (idEndereco == 0) {
				return 0;
			}
		}

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, idEndereco);
			// executa
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Pessoa inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir pessoa");
			}

			stmt.close();

			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa");
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public int atualizar(Pessoa pessoa) {
		this.getConexao();
		
		String sql = "UPDATE Pessoa SET nome = ?, sobrenome = ?, idendereco = ? WHERE idPESSOA = ?";

		Endereco endereco = pessoa.getEndereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		if (enderecoDAO.atualizar(endereco) == false) {
			return 0;
		}

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, pessoa.getEndereco().getId());
			stmt.setInt(4, pessoa.getId());

			// executa
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Pessoa atualizada com sucesso no BD!");
				return 1;
			} else {
			
				System.out.println("Erro ao atualizar Pessoa no BD!");
			
			}

			stmt.close();
			return 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Pessoa no BD!");
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean remover(int cdPessoa) {
		this.getConexao();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		if (enderecoDAO.remover(cdPessoa) == false)
			return false;

		boolean removidoSucesso = false;
		String sql = "DELETE FROM Pessoa WHERE idPESSOA = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cdPessoa);
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Pessoa removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover pessoa");
			}

			stmt.close();
			return removidoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao remover Pessoa no BD!");
			throw new RuntimeException(e);
		}
	}

	public Pessoa obterPessoa(int cdPessoa) {
		this.getConexao();
		Pessoa pessoa = null;

		String sql = "SELECT * FROM Pessoa WHERE idPESSOA = ?";

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.obter(cdPessoa);
		System.out.println("ENDERECO: " + endereco.getId() + " :: " + endereco.getRua());

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setMaxRows(1);
			stmt.setInt(1, cdPessoa);
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("idPESSOA"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSobrenome(rs.getString("sobrenome"));
				pessoa.setEndereco(endereco);
				System.out.println("PESSOA: " + pessoa.getId() + " :: " + pessoa.getEndereco().getRua());
			}

			stmt.close();
			return pessoa;

		} catch (SQLException e) {
			System.out.println("Erro ao buscar pessoas no BD!");
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public ArrayList<Pessoa> listar() {
		this.getConexao();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM Pessoa";
		try {

			PreparedStatement stmt = conexao.prepareStatement(sql);

			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = obterEnderecoResultSet(rs);
				pessoas.add(pessoa);
			}

			stmt.close();
			return pessoas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pessoas no BD!");
			return pessoas;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	private Pessoa obterEnderecoResultSet(ResultSet rs) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Pessoa pessoa = new Pessoa();
		try {
			pessoa.setId(rs.getInt("idPESSOA"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSobrenome(rs.getString("sobrenome"));
			pessoa.setEndereco(enderecoDAO.obter(pessoa.getId()));

			System.out.println(pessoa.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoa;
	}
}
