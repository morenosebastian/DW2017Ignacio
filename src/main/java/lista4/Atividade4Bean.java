package lista4;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade4BeanL4")
public class Atividade4Bean {

	public void alterar(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("trocado");
		pessoa.setSobrenome("alterado");
		pessoa.setId(1);
		Endereco endereco = new Endereco();
		endereco.setBairro("bairroTrocado");
		endereco.setNumero(1);
		endereco.setRua("ruaTrocado");
		endereco.setId(2);
		pessoa.setEndereco(endereco);
		PessoaDAO dao = new PessoaDAO();
		dao.atualizar(pessoa);
	}
	
	public void incluir(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("abc");
		pessoa.setSobrenome("dario");
		Endereco endereco = new Endereco();
		pessoa.setEndereco(endereco);
		endereco.setBairro("bairro");
		endereco.setNumero(20);
		endereco.setRua("rua");
		EnderecoDAO endDAO = new EnderecoDAO();
		endDAO.incluir(endereco);
		pessoa.setEndereco(endereco);
		PessoaDAO dao = new PessoaDAO();
		dao.incluir(pessoa);
	}
	
	public void excluir(){
		PessoaDAO dao = new PessoaDAO();
		dao.remover(2);
	}
	
	public void listar(){
		PessoaDAO dao = new PessoaDAO();
		dao.listar();
	}
}
