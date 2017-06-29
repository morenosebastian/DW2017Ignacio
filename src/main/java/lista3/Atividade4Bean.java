package lista3;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="atividade4Bean")
public class Atividade4Bean {

	private String nome;
	private String sexo;
	private String profissao;
	private ArrayList<Pessoa> listaPessoa = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public ArrayList<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(ArrayList<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public String salvarPessoa(){
		this.setNome(nome);
		this.setProfissao(profissao);
		this.setSexo(sexo);
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(this.getNome());
		pessoa.setProfissao(this.getProfissao());
		pessoa.setSexo(this.getSexo());
		listaPessoa.add(pessoa);
		return "ListaPessoas.xhtml";
	}
	
	public String voltar(){
		return "Atividade4.xhtml";
	}
}
