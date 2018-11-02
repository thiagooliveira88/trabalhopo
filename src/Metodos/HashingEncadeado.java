package Metodos;

import java.util.Date;

import Objetos.Promissoria;

public class HashingEncadeado {
	private ListaSimples[] vetorH;
	private int tamanho;

	public HashingEncadeado(int tam) {
		// aumenta o tamanho do vetor em 10%
		tam = (int) (tam * 1.1);
		// inicializa o vetor com o tamanho acrescido.
		this.setVetorH(new ListaSimples[tam]);
		this.setTamanho(tam);
		// para cada posição do vetor, inicializa uma lista encadeada.
		for (int i = 0; i < this.getTamanho(); i++) {
			this.getVetorH()[i] = new ListaSimples();
		}
	}

	public ListaSimples[] getVetorH() {
		return vetorH;
	}

	public void setVetorH(ListaSimples[] vetorH) {
		this.vetorH = vetorH;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public void inserir(Promissoria promi, int tam) {

		int pos = 0;
		Date data = promi.getdataVenc();
		pos = obterPosicaoHash(data, tam);
		this.getVetorH()[pos].inserirUltimo(promi);
	}

	private int obterPosicaoHash(Date data, int tam) {
		int mod = 0;
		int m = 0;
		char carac;
		for (int i = 0; i < data.toString().length(); i++) {

			carac = data.toString().charAt(i);
			m += Character.getNumericValue(carac);
		}

		switch (tam) {
		case 500:
			mod = (m % 503);
			break;
		case 1000:
			mod = (m % 1009);
			break;
		case 5000:
			mod = (m % 5003);
			break;
		case 10000:
			mod = (m % 10007);
			break;
		case 30000:
			mod = (m % 30011);
			break;
		}
		return mod;
	}
	
	public int pesquisaHash(Date data , int tam){
		String str = "";
		NoListaSimples aux;
		int pos = 0;
		pos = obterPosicaoHash(data, tam);
		if (pos>=0 && pos<this.tamanho){
			if (this.vetorH[pos].getPrim()!=null){
				aux = this.vetorH[pos].getPrim();
				while (aux != null){
					if (aux.getInfo().compareTo(data)==0)
						str += aux.getInfo().toString()+"\n";
					aux = aux.getProx();
				}
			}				
		}
		return -1;
	}
}
