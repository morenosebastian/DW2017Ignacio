package lista4;

public class Main {

	public static void main(String[] args) {
		
		Endereco endereco = new Endereco();
		endereco.setBairro("asdasd");
		endereco.setNumero(20);
		endereco.setRua("asdasd");
		Pessoa pessoa = new Pessoa();
		pessoa.setEndereco(endereco);
		pessoa.setNome("asdasd");
		pessoa.setSobrenome("asdasd");
		PessoaDAO pdao = new PessoaDAO();
		EnderecoDAO edao = new EnderecoDAO();
		edao.incluir(endereco);
		pdao.incluir(pessoa);

	}

}
