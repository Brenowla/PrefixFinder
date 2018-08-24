//Breno Washington Lage de Araujo - 15.1.8154

import java.util.ArrayList;
import java.util.Iterator;

public class Automato { //Representacao computacional de um automato
	
	private ArrayList<Estado> estados = new ArrayList<Estado>();
	private ArrayList<Estado> estadoFinal = new ArrayList<Estado>();
	private Estado estadoInicial;
	private ArrayList<Character> alfabeto = new ArrayList<Character>();
	
	public ArrayList<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(ArrayList<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public void setAlfabeto(char alfabeto) {
		this.alfabeto.add(alfabeto);
	}

	public Automato(){
	
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}

	public boolean addEstados(Estado estado) {
		return estados.add(estado);
	}

	public ArrayList<Estado> getEstadoFinal() {
		return estadoFinal;
	}

	public void addEstadoFinal(Estado estadoFinal) {
		this.estadoFinal.add(estadoFinal);
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	public boolean eFinal(Estado estado){
		return estadoFinal.contains(estado);
	}
	
	public boolean eInicial(Estado estado){
		return estado == estadoInicial;
	}
	
	public static void concatenaArrays(ArrayList a, ArrayList b){
		for(int i = 0; i<b.size();i++){
			a.add(b.get(i));
		}
	}
	
	public static Automato gerarAutomatoSimboloUnitario(char a,int index){ //Gera um automato que reconhece uma linguagem com apenas um simbolo
		Automato automato = new Automato();
		automato.setAlfabeto(a);
		automato.addEstados(new Estado (index, 0));
		automato.addEstados(new Estado (index,1));
		automato.setEstadoInicial(automato.getEstados().get(0));
		automato.addEstadoFinal(automato.getEstados().get(1));
		automato.getEstados().get(0).insereTransicao(new Transicao(automato.getEstados().get(1),a));
		automato.getAlfabeto().add(a);
		return automato;
	}
	
	public int analisadorTags(String Tag, int init){//Classifica o maior prefixo possivel da entrada atraves do automato
		Estado aux = this.estadoInicial;
		int cont = -1;
		for(int i=init; i<Tag.length(); i++){
			Transicao transicao = aux.encontrarTransicao(Tag.charAt(i));
			if(transicao == null){
				return cont;
			}
			aux = transicao.getDestino();
			if(eFinal(aux)){
				cont=i;
			}
		}
		return cont;
	}

	public void setEstadoFinal(ArrayList<Estado> estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}
	
	public Estado buscaEstado(int nome){
		for(int i=0;i<estados.size();i++){
			if(estados.get(i).getNome() == nome){
				return estados.get(i);
			}
		}
		return null;
	}
	
}
