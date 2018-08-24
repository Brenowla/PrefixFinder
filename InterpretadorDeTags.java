//Breno Washington Lage de Araujo - 15.1.8154
import java.util.ArrayList;

public class InterpretadorDeTags { //Implementa um metodo que varre as tags armazenadas classificando os prefixos da entrada

	public static String InterpretadorDeTags(Listadetags tags, String entrada){
		int i = -1;
		boolean verif = false;
		ArrayList<String> nomes = new <String>ArrayList();
		ArrayList<ArrayList> listacolisoes = new <ArrayList>ArrayList();
		while(i+ 1< entrada.length()){
			ArrayList<String> colisoes = new <String>ArrayList();
			int[] leitura = new int[tags.getTag().size()];
			for(int j = 0; j<tags.getTag().size();j++){
				leitura[j] = tags.getTag().get(j).getAutomato().analisadorTags(entrada, i+1);
			}
			int aux =0;
			for(int j = 0; j<leitura.length; j++){
				if( leitura[j] >=0 && aux == 0){
					i = leitura[j];
					nomes.add(tags.getTag().get(j).getNome());
					colisoes.add(tags.getTag().get(j).getNome());
					aux +=1;
				}
				else if(leitura[j] >=0){
					colisoes.add(tags.getTag().get(j).getNome());
				}
			}
			if(!listacolisoes.contains(colisoes) && colisoes.size()>1){
				listacolisoes.add(colisoes);
			}
			if(aux == 0){
				System.out.println("[INFO] Nao foi possivel classificar toda a entrada");
				break;
			}
		}
		for(i = 0; i<listacolisoes.size();i++){
			System.out.println("[WARNING] Sobreposicao na classificacao da entrada com as Tags: " + listacolisoes.get(i).toString());
		}
		String saida = "";
		for(i=0; i<nomes.size();i++){
			saida += nomes.get(i) + " ";
		}
		return saida;
	}
}
	
