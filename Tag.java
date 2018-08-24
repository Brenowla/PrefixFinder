//Breno Washington Lage de Araujo - 15.1.8154

public class Tag {
	
	private String nome= "";
	private String definicao;
	private Automato automato;
	
	public Tag(){
	}

	public Tag(String definicao){
	this.definicao = definicao;
	}

	public String getNome() {
		return nome;
	}

	public String getDefinicao() {
		return definicao;
	}

	 public int Analisadordetag(){ //Utiliza uma pilha para analisar se a tag inserida esta correta ou possui erros na formulacao ao mesmo tempo que gera o automato correspondente a ela
		 PilhaAutomato pilha = new PilhaAutomato();
		 int i, ver, limit = 0,flag = 0;
		 char aux;
		 for(i=0; i<definicao.length() && definicao.charAt(i) != ':' ;i++){
			 nome += definicao.charAt(i);
		 }
		 i+=2;
		 if (i>= definicao.length()){
			 return  0;
		 }
		 while(i<definicao.length()){
			 Automato aux1 = new Automato();
			 Automato aux2 = new Automato();
			 aux=definicao.charAt(i);
			 ver = verficadordechar(aux);
			 if(ver == 1){
			 i++;
			 aux=definicao.charAt(i);
			 ver=verficadordechar(aux);
			 	if(ver != 0 && ver != 6){
			 		pilha.empilhar(Automato.gerarAutomatoSimboloUnitario(aux, i));
			 	}else if(ver == 6){
			 		pilha.empilhar(Automato.gerarAutomatoSimboloUnitario((char)10, i));
			 	}else{
			 		return 0;
			 	}
			 }else if(ver == 0 || ver >= 5){
				 pilha.empilhar(Automato.gerarAutomatoSimboloUnitario(aux, i));
			 }else if( ver == 2 || ver == 4){
				 limit = 2;
				 while(pilha.Tamanho()>0 && limit>0){
				 	if(limit==2){
				 		aux2 = pilha.desempilhar();
				 	}
				 	else{
				 		aux1 = pilha.desempilhar();
				 	}
				 	limit--;
				 }
				 if(limit >=1){
					 return 0;
				 }
				 if(ver ==2){
					 pilha.empilhar(OperacoesAutomatos.concatenaAutomatos(aux1, aux2));
				 }
				 else{
					 pilha.empilhar(OperacoesAutomatos.uniaoAutomato(aux1, aux2, i));
				 }
		 	}else if( ver==3){
		 		if(pilha.Tamanho()>0){
		 			aux1 = pilha.desempilhar();
		 			pilha.empilhar(OperacoesAutomatos.kleeneAutomatos(aux1));
		 		}
		 		else{
		 			return 0;
		 		}
		 	}
			 i++;
			 
		 }
		 if (pilha.Tamanho() == 1){
			 automato = pilha.desempilhar();
			 return 1;
		 }
		 else{
			 return 0;
		 }
	 }
	 
	 public Automato getAutomato() {
		return automato;
	}

	public int verficadordechar(char c){ //Utilizado para verificar os caracteres de escape que podem ser inseridos na tag
			if (c == (char)92){
				return 1;
			}
			if (c == '.'){
				return 2;
			}
			if (c == '*'){
				return 3;
			}
			if (c == '+'){
				return 4;
			}
			if(c == 'l'){
				return 5;
			}
			if(c == 'n'){
				return 6;
			}
			else return 0;
		}
		

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}


		
}
