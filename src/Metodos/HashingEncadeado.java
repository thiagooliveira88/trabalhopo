package Metodos;

import Objetos.ListaSimples;
import Objetos.NoListaSimples;
import Objetos.Promissoria;
import Utils.UsoGeral;

public class HashingEncadeado {
	private ListaSimples[] vetorH;
	private int tamanho;

	public HashingEncadeado(int tam) {
		// aumenta o tamanho do vetor em 10%
		tam = this.obterTamanhoVetor(tam);
		// inicializa o vetor com o tamanho acrescido.
		this.setVetorH(new ListaSimples[tam]);
		this.setTamanho(tam);
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

	public void inserir(Promissoria promi) {

		// pesquisa a data
		NoListaSimples noNovo = pesquisaHash(promi.getdataVenc());
		if (noNovo != null) {

			NoListaSimples temp = noNovo;
			while (temp != null && temp.getNoDataRepetida() != null) {
				temp = temp.getNoDataRepetida();
			}
			// se chegou até aqui, quer dizer que o NoRepetido esta nulo
			// insiro o elemento repetido.
			temp.setNoDataRepetida(new NoListaSimples(promi));

		} else {

			int pos = 0;
			pos = obterPosicaoHash(promi.getdataVenc());

			if (this.getVetorH()[pos] == null) {
				this.getVetorH()[pos] = new ListaSimples();
				this.getVetorH()[pos].inserirUltimo(promi);
			} else {
				this.getVetorH()[pos].inserirUltimo(promi);
			}

		}

	}

	private int obterPosicaoHash(int data) {
		return (data % this.tamanho);
	}

	private int obterTamanhoVetor(int tam) {

		int tamanhoVet = 0;

		switch (tam) {
		case 500:
			tamanhoVet = 557;
			break;
		case 1000:
			tamanhoVet = 1103;
			break;
		case 5000:
			tamanhoVet = 5503;
			break;
		case 10000:
			tamanhoVet = 11003;
			break;
		case 30000:
			tamanhoVet = 33013;
			break;
		}

		return tamanhoVet;
	}

	public Object pesquisar(int data) {

		NoListaSimples noData = pesquisaHash(data);

		if (noData != null)
			return noData;

		return UsoGeral.converterIntToString(data);
	}

	private NoListaSimples pesquisaHash(int data) {
		NoListaSimples aux;
		int pos = 0;
		pos = obterPosicaoHash(data);
		if (pos >= 0 && pos < this.tamanho) {
			if (this.vetorH[pos] != null && this.vetorH[pos].getPrim() != null) {
				aux = this.vetorH[pos].getPrim();
				while (aux != null) {

					if (aux.getInfo().getdataVenc() == data) {
						return aux;
					} else {
						aux = aux.getProx();
					}
				}
			}
		}
		return null;
	}
}
