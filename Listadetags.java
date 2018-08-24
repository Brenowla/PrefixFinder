//Breno Washington Lage de Araujo - 15.1.8154

import java.util.ArrayList;
import java.util.Collections;

public class Listadetags { //Lista das tags inseridas durante o trabalho
	
	ArrayList <Tag>tags = new <Tag>ArrayList();

	public ArrayList<Tag> getTag() {
		return tags;
	}
	
	public void adicionar(Tag t){
		for(int i=0; i< tags.size();i++){
			int aux = OperacoesAutomatos.intercesaoAutomato(tags.get(i).getAutomato(), t.getAutomato(), 0);
			if(aux == 1){
				System.out.println("[WARNING]  Sobreposicao na definicao das tags "+ tags.get(i).getNome() + " e " + t.getNome() +".");
			}
		}
		tags.add(t);
	}
	
	
	public int Definicoes(ArrayList<String> def){
		int verif;
		try{
		for (int i=0; i< def.size();i++){
			Tag aux = new Tag();
			aux.setDefinicao(def.get(i));
			verif = aux.Analisadordetag();
			if (verif == 0){
				System.out.println("[ERROR] Definicao de tag incorreta! Tag numero " + (i+1));
			}
			else{
				boolean ver = verificarTags(aux.getDefinicao());
				if(ver == false){
					System.out.println("[ERROR] Dupla definicao de tags: " + aux.getNome());
				}
				else{
					adicionar(aux);
				}
			}
			
		}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
	
	public boolean verificarTags(String definicao){
		for (int i=0; i< tags.size();i++){
			if(tags.get(i).getNome().equals(definicao)){
				return false;
			}
		}
		return true;
		
	}
}
