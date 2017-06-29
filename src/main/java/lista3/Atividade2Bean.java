package lista3;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade2Bean")
public class Atividade2Bean {

	private String tipoSelecionado;
	private ArrayList<String> lista = this.gerarAnimais();
	private ArrayList<String> lista2 = this.gerarFrutas();
	
	public ArrayList<String> getLista2() {
		return lista2;
	}

	public void setLista2(ArrayList<String> lista2) {
		this.lista2 = lista2;
	}

	public void gerarLista(){
		ArrayList<String> lista = new ArrayList<>();
		if(getTipoSelecionado() != null){
			if(getTipoSelecionado().equalsIgnoreCase("animal")){
				lista = gerarAnimais();
			}else{
				lista = gerarFrutas();
			}
		}
		this.setLista(lista);
	}
	
	public ArrayList<String> gerarAnimais(){
		
		Animal a1 = new Animal("Papagaio");
		Animal a2 = new Animal("Coruja");
		Animal a3 = new Animal("Cachorro");
		
		ArrayList<String> animais = new ArrayList<>();
		animais.add(a1.getNome());
		animais.add(a2.getNome());
		animais.add(a3.getNome());
		
		return animais;
	}
	
	public ArrayList<String> gerarFrutas(){
		
		Fruta a1 = new Fruta("Laranja");
		Fruta a2 = new Fruta("Tomate");
		Fruta a3 = new Fruta("Abacaxi");
		
		ArrayList<String> frutas = new ArrayList<>();
		frutas.add(a1.getNome());
		frutas.add(a2.getNome());
		frutas.add(a3.getNome());
		
		return frutas;
	}
	
	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}

	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}
	
	
}
