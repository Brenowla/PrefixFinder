//Breno Washington Lage de Araujo - 15.1.8154
import java.util.ArrayList;

public class Estado { //Classe que representa um estado do automato

	int index;
	int nome;
	ArrayList<Transicao> transicoes = new ArrayList<Transicao>();
	
	public Estado(int index,int nome){
		this.index = index;
		this.nome = nome;
	}
	
	public void insereTransicao(Transicao transicao){
		transicoes.add(transicao);
	}
	
	public Transicao encontrarTransicao(Estado destino){
		for(int i = 0; i<transicoes.size(); i++){
			if(transicoes.get(i).getDestino().equals(destino)){
				return transicoes.get(i);
			}
		}
		return null;
	}
	
	public Transicao encontrarTransicao(char simbolo){
		for(int i = 0; i<transicoes.size(); i++){
			if(transicoes.get(i).getSimbolo() == simbolo){
				return transicoes.get(i);
			}
		}
		return null;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}

	public ArrayList<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}

	
}
