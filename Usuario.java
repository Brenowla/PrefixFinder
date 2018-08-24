//Breno Washington Lage de Araujo - 15.1.8154


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Usuario { //Classe responsavel por fornecer a interacao com o usuario

	private static Listadetags ltags = new Listadetags();
	
	public static void comandos(){
		String entrada = "";
		ArrayList<String> carregar = new <String>ArrayList();
		int verif = 0, aux = 0;
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		while (verif != 6){
			try {
				entrada = leitor.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			aux = 0;
			verif = Interpretador.interpretar(entrada);
			if(verif == 1){
				Tag gerar = new Tag();
				gerar.setDefinicao(entrada);
				aux = gerar.Analisadordetag();
				if (aux == 0){
					System.out.println("[ERROR] Definicao de tag nao correta!");
				}
				else{
					if(ltags.verificarTags(gerar.getNome())){
						ltags.adicionar(gerar);
					}
					else{
						System.out.println("[ERROR] Dupla definicao de tags!");			
					}
				}
			}
			if( verif == 2){
				String divisao = InterpretadorDeTags.InterpretadorDeTags(ltags, ModulodeArquivos.carregaArquivoCompleto(Interpretador.leitordenomes(entrada)));
				System.out.println(divisao);
				ModulodeArquivos.escreverDivisaoemTAGS(divisao);
			}
			if( verif == 3){
				if(entrada.length()>= 3 && entrada.charAt(2) == ' '){
					carregar = ModulodeArquivos.carregararquivodetags(Interpretador.leitordenomes(entrada));
					aux = ltags.Definicoes(carregar);
					if(aux==0){
						System.out.println("[ERROR] Definicao de tag nao correta!");
					}
					System.out.println("[INFO] Arquivo carregado com sucesso!");
				}
				else{
					System.out.println("[ERROR] Caminho invalido!");
				}
			}
			if (verif == 4){
				if(entrada.length()>= 3 && entrada.charAt(2) == ' '){
					ModulodeArquivos.setArquivodesaida(Interpretador.leitordenomes(entrada));
					System.out.println("[INFO] Novo nome do arquivo de saida das divisoes em tags: " + Interpretador.leitordenomes(entrada));
				}
				else{
					System.out.println("[ERROR] Caminho invalido!");
				}
			}
			if(verif == 5){
				String divisao = InterpretadorDeTags.InterpretadorDeTags(ltags, Interpretador.leitordenomes(entrada));
				System.out.println(divisao);
				ModulodeArquivos.escreverDivisaoemTAGS(divisao);
			}
			if(verif == 7){
				ModulodeArquivos.escreverarquivodetags(Interpretador.leitordenomes(entrada), ltags);
			}
			if(verif == 8){
				System.out.println("[ERROR] Comando invalido");
			}
		}
	}
	
}
