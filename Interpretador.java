//Breno Washington Lage de Araujo - 15.1.8154
public class Interpretador {

	static int interpretar(String comando){ //Verifica se a instrucao do usuario e um comando ou uma possivel tag
		if(comando.length()==0){
			return 8;
		}
		if(comando.charAt(0)==':'){
			return interpretadordecomandos(comando);
		}
		else return 1;
		
	}
	
	static int interpretadordecomandos(String comando){ //verifica se o comando do usuario e valido
		if(comando.length()==1){
			return 8; 
		}
		if(comando.charAt(1) == 'f'){
			return 2;
		}
		if(comando.charAt(1) == 'l'){
			return 3;
		}
		if(comando.charAt(1) == 'o'){
			return 4;
		}
		if(comando.charAt(1) == 'p'){
			return 5;
		}
		if(comando.charAt(1) == 'q'){
			return 6;
		}
		if(comando.charAt(1) == 's'){
			return 7;
		}
		else{
			return 8;
		}
	}
	
	public static String leitordenomes(String comando){ //utilizado para ler a sequencia de caracteres existentes pos alguns comandos
		String nome = "";
		for(int i=3; i<comando.length();i++){
			nome += comando.charAt(i);
		}
		return nome;
	}
}
