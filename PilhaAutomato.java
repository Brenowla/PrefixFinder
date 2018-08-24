//Breno Washington Lage de Araujo - 15.1.8154
import java.util.ArrayList;
import java.util.List;

public class PilhaAutomato { //Utilizada como estrutura auxiliar para a afericao da tag e geracao de seu automato correspondente

private List <Automato>Pilha;
	
	public PilhaAutomato(){
		Pilha = new <Automato>ArrayList();
	}

	public void empilhar(Automato objeto){
		Pilha.add(objeto);
	}
	
	public Automato desempilhar(){
		Automato aux;
		aux = Pilha.get(Pilha.size()-1);
		Pilha.remove(Pilha.size()-1);
		return aux;
	}
	
	public int Tamanho(){
		return Pilha.size();
	}
	
	public void limpa(){
		Pilha.clear();
	}
	
}
