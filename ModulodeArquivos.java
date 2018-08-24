//Breno Washington Lage de Araujo - 15.1.8154

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ModulodeArquivos {

	private static String Arquivodesaida; //armazena o nome do arquivo padrao de saida para as divisoes da em entrada nas tags
	private static int verif = 0;
	
	public static void setArquivodesaida(String arquivodesaida) {
		Arquivodesaida = arquivodesaida;
		verif = 1;
	}



	public static void escreverarquivodetags (String nome,Listadetags lista){ //escreve em um arquivo, determinado pelo usuario, as tags definidas ate o momento no programa
		try {
			File arquivoescrita = new File(nome);
			FileWriter arqescritor = new FileWriter(arquivoescrita);
			BufferedWriter escritor = new BufferedWriter(arqescritor);
			for(int i=0; i<lista.getTag().size(); i++){
				escritor.write(lista.getTag().get(i).getDefinicao());
				escritor.newLine();
			}
			escritor.close();
			System.out.println("[INFO] Tags salvas no arquivo: " + nome);
		} catch (IOException e) {
			System.out.println("[ERROR] Arquivo nao carregado");
		}
		
	}
	
	public static ArrayList<String> carregararquivodetags(String nome){ //Le um arquivo, determinado pelo usuario, uma possivel lista de tags
		try {
			File arquivoleitura = new File(nome);
			FileReader arqleitura = new FileReader(arquivoleitura);
			BufferedReader leitor = new BufferedReader(arqleitura);
			ArrayList <String>tagsdoarquivo = new ArrayList<String>();
			while(leitor.ready()){
				tagsdoarquivo.add(leitor.readLine());
			}
			leitor.close();
			return tagsdoarquivo;
		} catch (IOException e) {
			System.out.println("[ERROR] Nao foi possivel carregar o arquivo de tags");
		}
			return null;
		}
	
	public static String carregaArquivoCompleto(String entrada){//Le todo arquivo de entrada para classificacao
		try {
			File arquivoleitura = new File(entrada);
			FileReader arqleitura;
			arqleitura = new FileReader(arquivoleitura);
			BufferedReader leitor = new BufferedReader(arqleitura);
			String arquivo = "";
			String aux = null;
			while((aux = leitor.readLine()) != null){
				arquivo += aux;
				arquivo += "\n";
			}
			return arquivo;
		} catch (Exception e1) {
		}
		return "";
	}
	
	public static void escreverDivisaoemTAGS(String divisao){ //Escreve a divisao da entrada no arquivo informado anteriormente
		try {
			if(Arquivodesaida == null){
				return;
			}
			if(verif == 1){
				BufferedWriter saida = new BufferedWriter(new FileWriter(Arquivodesaida,true));
				saida.write(divisao);
				saida.newLine();
				saida.close();
				verif = 0;
			}
			else{
				BufferedWriter saida = new BufferedWriter(new FileWriter(Arquivodesaida,true));
				saida.write(divisao);
				saida.write('\n');
				saida.close();
			}
			
		} catch (IOException e) {
			System.out.println("[ERRO] Nao foi possivel salvar a divisao da entrada em TAGS!");
		}
	}
	
}
