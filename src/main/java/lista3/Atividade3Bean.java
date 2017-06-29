package lista3;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade3Bean")
public class Atividade3Bean {

	private String tipoSelecionado;
	private ArrayList<Animal> listaAnimal = new ArrayList<>();
	private ArrayList<Fruta> listaFruta = new ArrayList<>();
	
	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public ArrayList<Animal> getListaAnimal() {
		return listaAnimal;
	}

	public void setListaAnimal(ArrayList<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}

	public ArrayList<Fruta> getListaFruta() {
		return listaFruta;
	}

	public void setListaFruta(ArrayList<Fruta> listaFruta) {
		this.listaFruta = listaFruta;
	}

	public void gerarLista(){
		ArrayList<String> lista = new ArrayList<>();
		if(getTipoSelecionado() != null){
			if(getTipoSelecionado().equalsIgnoreCase("animal")){
				this.setListaAnimal(gerarAnimais());
			}else{
				this.setListaFruta(gerarFrutas());
			}
		}
	}
	
public ArrayList<Animal> gerarAnimais(){
		
		Animal papagaio = new Animal("Papagaio", 500, "Ave");
		Animal tainha = new Animal("Tainha", 2000, "Peixe");
		Animal cachorro = new Animal("Cachorro", 5000, "Mamífero");
		
		ArrayList<Animal> animais = new ArrayList<>();
		animais.add(papagaio);
		animais.add(tainha);
		animais.add(cachorro);
		
		return animais;
	}
	
	public ArrayList<Fruta> gerarFrutas(){
		
		Fruta laranja = new Fruta("Laranja", 50, "Azedo");
		Fruta tomate = new Fruta("Tomate", 45, "Salgado");
		Fruta uva = new Fruta("Uva", 30, "Doce");
		
		ArrayList<Fruta> frutas = new ArrayList<>();
		frutas.add(laranja);
		frutas.add(tomate);
		frutas.add(uva);
		
		return frutas;
	}
}
