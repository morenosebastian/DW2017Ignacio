package lista4;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade5Bean")
public class Atividade5Bean {

	private String nome;
	private String sobrenome;
	private String rua;
	private String bairro;
	private int numero;
	
	public void incluirPessoa(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(this.getNome());
		pessoa.setSobrenome(this.getSobrenome());
		Endereco endereco = new Endereco();
		endereco.setBairro(this.getBairro());
		endereco.setRua(this.getRua());
		endereco.setNumero(this.getNumero());
		pessoa.setEndereco(endereco);
		PessoaDAO dao = new PessoaDAO();
		dao.incluir(pessoa);
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
