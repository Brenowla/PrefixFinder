//Breno Washington Lage de Araujo - 15.1.8154
import java.util.ArrayList;

public class OperacoesAutomatos { //Realiza operacoes fundamentais sobre os automatos
	
	public static Automato kleeneAutomatos(Automato a){ //Realiza operacao de fecho de kleene
		int marcador = a.getEstados().size();
		for(int i = 0; i< a.getEstadoFinal().size(); i++){
			trocaTransicoesKleene(a.getEstadoFinal().get(i),a.getEstadoInicial(),a, marcador);
		}
		for(int i = 0; i< a.getEstadoFinal().size(); i++){
			if(a.getEstadoFinal().get(i).getTransicoes().containsAll((a.getEstadoInicial().getTransicoes())));{
				a.setEstadoInicial(a.getEstadoFinal().get(i));
			}
		}
		if(!a.getEstadoFinal().contains(a.getEstadoInicial())){
			a.getEstadoFinal().add(a.getEstadoInicial());
		}
		return a;
	}

	public static void trocaTransicoesKleene(Estado a, Estado b,Automato aut, int marcador){
		for(int i = 0; i<b.getTransicoes().size(); i++){
			if(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()) == null){
				if(b.getTransicoes().get(i).getDestino().equals(a) && !aut.eFinal(a)){
					Estado aux = new Estado(a.getIndex(),aut.getEstados().size());
					aux.setTransicoes((ArrayList<Transicao>)b.getTransicoes().clone());
					aut.addEstados(aux);
					a.insereTransicao(new Transicao(aux, b.getTransicoes().get(i).getSimbolo()));
						if(aut.eFinal(a)){
							aut.addEstadoFinal(aux);
						}
				}
				else{
					a.insereTransicao(b.getTransicoes().get(i));
				}
			}
			else {
				if(marcador>0 && !b.getTransicoes().get(i).getDestino().equals(a) && !b.getTransicoes().get(i).getDestino().equals(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino()) && !(b.getTransicoes().get(i).getDestino().equals(b) && a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino().equals(a))){
					trocaTransicoesKleene(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino(),b.getTransicoes().get(i).getDestino(),aut,marcador-1);
				}
			}
		}
	}
	
	public static Automato concatenaAutomatos(Automato a, Automato b){ //Realiza operacao de concatenacao sobre dois automatos
		int marcador = b.getEstados().size() - 1;
		int i;
		int x = a.getEstadoFinal().size();
		ArrayList<Estado> estados = new ArrayList();
		for (int j =0; j< b.getAlfabeto().size(); j++){
			if(!(a.getAlfabeto().contains(b.getAlfabeto().get(j)))){
				a.getAlfabeto().add(b.getAlfabeto().get(j));
			}
		}
		for( i = 0; i< x; i++){
			trocaTransicoesConcatenacao(a.getEstadoFinal().get(i),b.getEstadoInicial(),marcador,a,b,estados,false);
		}
		a.getEstadoFinal().clear();
		a.getEstadoFinal().addAll(estados);
		for(int k =0; k<a.getEstados().size();k++){
			for(int j = 0; j<a.getEstados().get(k).getTransicoes().size(); j++){
				if(!a.getEstados().contains(a.getEstados().get(k).getTransicoes().get(j).getDestino())){
					a.addEstados(a.getEstados().get(k).getTransicoes().get(j).getDestino());
				}
			}
		}
		for (int j =0; j< b.getEstadoFinal().size(); j++){
			if(a.getEstados().contains(b.getEstadoFinal().get(j))){
				a.getEstadoFinal().add(b.getEstadoFinal().get(j));
			}
		}
		if(a.getEstadoInicial().getNome() != 0  && !b.eFinal(b.getEstadoInicial())){
			a.getEstadoFinal().remove(a.getEstadoInicial());
		}
		return a;
	}
	
	public static void trocaTransicoesConcatenacao(Estado a, Estado b, int marcador, Automato prim, Automato seg, ArrayList<Estado> estados,boolean verif){
		for(int i = 0; i<b.getTransicoes().size(); i++){
			if(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()) == null){
				a.insereTransicao(b.getTransicoes().get(i));
			}else{
				if(marcador>0){
					if((prim.eFinal(a)  && !seg.eFinal(seg.getEstadoInicial())) || verif ){
						if(a.getTransicoes().equals(prim.getEstadoInicial())){		
							prim.setEstadoInicial(a);
						}
						Estado novo = new Estado (a.getIndex(),prim.getEstados().size());
						if(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino().equals(a)){
							novo.insereTransicao(new Transicao(novo, b.getTransicoes().get(i).getSimbolo()));
						}
						for(int k=0; k<a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino().getTransicoes().size();k++){
							if(novo.encontrarTransicao(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino().getTransicoes().get(k).getSimbolo()) == null){
								novo.getTransicoes().add(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino().getTransicoes().get(k));
							}
						}
						prim.addEstados(novo);
						if(verif == false){
							a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).setDestino(novo);
						}
						else{
							a.getTransicoes().remove(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()));
							a.insereTransicao(new Transicao(novo,b.getTransicoes().get(i).getSimbolo()));
						}
						verif =true;
					}
					int c = marcador - 1;
					trocaTransicoesConcatenacao(a.encontrarTransicao(b.getTransicoes().get(i).getSimbolo()).getDestino(),b.getTransicoes().get(i).getDestino(),c,prim,seg,estados,verif);
				}
			}	
			if(seg.eFinal(b) && !estados.contains(a)){
					estados.add(a);
			}			
		}
		if(seg.eFinal(b) && !estados.contains(a)){
			estados.add(a);
		}
	}
	
	public static Automato uniaoAutomato(Automato a, Automato b, int index){ //Realiza operacao de uniao entre dois automatos
			Automato aux = new Automato();
			if(a.getEstados().size() == 2 && b.getEstados().size() == 2 && a.getEstadoFinal().size() == b.getEstadoFinal().size()){
				aux =a;
				for(int i =0; i< 2;i++){
					for(int j=0;j<b.getEstados().get(i).getTransicoes().size();j++){
					if(aux.getEstados().get(i).encontrarTransicao(b.getEstados().get(i).getTransicoes().get(j).getSimbolo()) == null){
						aux.getEstados().get(i).insereTransicao(new Transicao(aux.getEstados().get(b.getEstados().get(i).getTransicoes().get(j).getDestino().nome),b.getEstados().get(i).getTransicoes().get(j).getSimbolo()));
					}
					}
				}
			}
			else{
				ArrayList<Estado[]> uniao = new ArrayList();
				Estado[] estadosUniao = new Estado[3];
				estadosUniao[0] = a.getEstadoInicial();
				estadosUniao[1] = b.getEstadoInicial();
				estadosUniao[2] = new Estado(index, aux.getEstados().size());
				uniao.add(estadosUniao);
				aux.addEstados(estadosUniao[2]);
				aux.setEstadoInicial(estadosUniao[2]);
				concatenaAlfabetos(aux, a, b);
				for(int i = 0; i<uniao.size(); i++){
					for(int j=0; j<aux.getAlfabeto().size();j++){
						Estado[] estadosAux = new Estado[3];
						if(uniao.get(i)[0] != null && uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
							estadosAux[0] = uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
							
						}
						if(uniao.get(i)[1] != null && uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
							estadosAux[1] = uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
						}
						estadosAux[2] = buscaEstadosUniao(estadosAux[0],estadosAux[1],uniao);
						if(estadosAux[2] == null && !(estadosAux[0] ==null && estadosAux[1] ==null)){
							estadosAux[2] = new Estado(index,aux.getEstados().size());
							uniao.add(estadosAux);
							aux.addEstados(estadosAux[2]);
							aux.getEstados().get(i).insereTransicao(new Transicao(aux.getEstados().get(aux.getEstados().size()-1),aux.getAlfabeto().get(j)));
						}
						else if(estadosAux[2] != null && !(estadosAux[0] ==null && estadosAux[1] ==null)){
							aux.getEstados().get(i).insereTransicao(new Transicao(estadosAux[2],aux.getAlfabeto().get(j)));
						}
					}
				}
				for(int i=0; i<uniao.size();i++){
					if(a.eFinal(uniao.get(i)[0]) || b.eFinal(uniao.get(i)[1])){
						aux.addEstadoFinal(uniao.get(i)[2]);
					}
				}
			}
		return aux;
	}
	
	public static Estado buscaEstadosUniao(Estado a, Estado b, ArrayList<Estado[]> aux){
		int verif ;
		for(int i =0; i<aux.size();i++){
			verif=0;
			if(aux.get(i)[0] == null || a == null){
				if(aux.get(i)[0] == null && a == null){
					verif +=1;
				}
			}else if(aux.get(i)[0].equals(a)){
				verif+=1;
			}
			if(aux.get(i)[1] == null || b ==null){
				if(aux.get(i)[1] == null && b == null){
					verif +=1;
				}
			}else if(aux.get(i)[1].equals(b)){
				verif+=1;
			}
			if(verif == 2){
				return aux.get(i)[2];
			}
		}
		return null;
	}
	
	public static void concatenaAlfabetos(Automato prim, Automato a, Automato b){
		for(int i = 0; i<a.getAlfabeto().size(); i++){
			if(!prim.getAlfabeto().contains(a.getAlfabeto().get(i))){
				prim.setAlfabeto(a.getAlfabeto().get(i));
			
			}
		}
		for(int i = 0; i<b.getAlfabeto().size(); i++){
			if(!prim.getAlfabeto().contains(b.getAlfabeto().get(i))){
				prim.setAlfabeto(b.getAlfabeto().get(i));
			
			}
		}
	}
	
	public static int intercesaoAutomato(Automato a, Automato b, int index){//Realiza operacao de fecho de intercecao entre dois automatos
		Automato aux = new Automato();
		ArrayList<Estado[]> uniao = new ArrayList();
		Estado[] estadosUniao = new Estado[3];
		estadosUniao[0] = a.getEstadoInicial();
		estadosUniao[1] = b.getEstadoInicial();
		estadosUniao[2] = new Estado(index, aux.getEstados().size());
		uniao.add(estadosUniao);
		aux.addEstados(estadosUniao[2]);
		aux.setEstadoInicial(estadosUniao[2]);
		concatenaAlfabetos(aux, a, b);
		for(int i = 0; i<uniao.size(); i++){
			for(int j=0; j<aux.getAlfabeto().size();j++){
				Estado[] estadosAux = new Estado[3];
				if(uniao.get(i)[0] != null && uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
					estadosAux[0] = uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
					
				}
				if(uniao.get(i)[1] != null && uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
					estadosAux[1] = uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
				}
				estadosAux[2] = buscaEstadosUniao(estadosAux[0],estadosAux[1],uniao);
				if(estadosAux[2] == null && !(estadosAux[0] ==null && estadosAux[1] ==null)){
					estadosAux[2] = new Estado(index,aux.getEstados().size());
					uniao.add(estadosAux);
					aux.addEstados(estadosAux[2]);
					aux.getEstados().get(i).insereTransicao(new Transicao(aux.getEstados().get(aux.getEstados().size()-1),aux.getAlfabeto().get(j)));
				}
				else if(estadosAux[2] != null){
					aux.getEstados().get(i).insereTransicao(new Transicao(estadosAux[2],aux.getAlfabeto().get(j)));
				}
			}
		}
		for(int i=0; i<uniao.size();i++){
			if(a.eFinal(uniao.get(i)[0]) && b.eFinal(uniao.get(i)[1]) && !aux.eInicial(uniao.get(i)[2])){
				aux.addEstadoFinal(uniao.get(i)[2]);
			}
		}
		if(aux.getEstadoFinal().size()>0){
				return 1;
		}
		else{
			return 0;
		}
	}
	
	public static ArrayList<Estado> intercesaoAutomatoConcat(Automato a, Automato b, int index){
		Automato aux = new Automato();
		ArrayList<Estado[]> uniao = new ArrayList();
		Estado[] estadosUniao = new Estado[3];
		estadosUniao[0] = a.getEstadoInicial();
		estadosUniao[1] = b.getEstadoInicial();
		estadosUniao[2] = new Estado(index, aux.getEstados().size());
		uniao.add(estadosUniao);
		aux.addEstados(estadosUniao[2]);
		aux.setEstadoInicial(estadosUniao[2]);
		concatenaAlfabetos(aux, a, b);
		for(int i = 0; i<uniao.size(); i++){
			for(int j=0; j<aux.getAlfabeto().size();j++){
				Estado[] estadosAux = new Estado[3];
				if(uniao.get(i)[0] != null && uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
					estadosAux[0] = uniao.get(i)[0].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
					
				}
				if(uniao.get(i)[1] != null && uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)) != null){
					estadosAux[1] = uniao.get(i)[1].encontrarTransicao(aux.getAlfabeto().get(j)).getDestino();
				}
				estadosAux[2] = buscaEstadosUniao(estadosAux[0],estadosAux[1],uniao);
				if(estadosAux[2] == null && !(estadosAux[0] ==null && estadosAux[1] ==null)){
					estadosAux[2] = new Estado(index,aux.getEstados().size());
					uniao.add(estadosAux);
					aux.addEstados(estadosAux[2]);
					aux.getEstados().get(i).insereTransicao(new Transicao(aux.getEstados().get(aux.getEstados().size()-1),aux.getAlfabeto().get(j)));
				}
				else if(estadosAux[2] != null){
					aux.getEstados().get(i).insereTransicao(new Transicao(estadosAux[2],aux.getAlfabeto().get(j)));
				}
			}
		}
		ArrayList<Estado> estados = new <Estado>ArrayList();
		for(int i=0; i<uniao.size();i++){
			if(a.eFinal(uniao.get(i)[0]) && b.eFinal(uniao.get(i)[1]) && !aux.eInicial(uniao.get(i)[2])){
				if(uniao.get(i)[2].getTransicoes().size()>0 && !estados.contains(uniao.get(i)[0])){
					estados.add(uniao.get(i)[0]);
				}
			}
		}
		return estados;
	}
	
}
