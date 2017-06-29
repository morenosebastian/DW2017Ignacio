package lista3;

public class Pessoa {
	
	private String nome;
	private String profissao;
	private String sexo;
	
	public Pessoa(){
		
	}
	
	public Pessoa(String nome, String profissao, String sexo) {
		super();
		this.nome = nome;
		this.profissao = profissao;
		this.sexo = sexo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}
