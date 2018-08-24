//Breno Washington Lage de Araujo - 15.1.8154

public class Transicao implements Cloneable{//Representa as transicoes existentes entre os estados de um automato

	Estado destino;
	char simbolo;
	
	public Transicao(Estado destino,char simbolo){
		this.destino = destino;
		this.simbolo = simbolo;
	}

	public Estado getDestino() {
		return destino;
	}

	public void setDestino(Estado destino) {
		this.destino = destino;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	
}
