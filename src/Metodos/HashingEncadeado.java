package Metodos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import Objetos.Promissoria;

public class HashingEncadeado {
	private ListaSimples[] vetorH;
	private int tamanho;

	public HashingEncadeado(int tam) {
		// aumenta o tamanho do vetor em 10%
		tam = this.obterTamanhoVetor(tam);
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
			this.getVetorH()[pos].inserirUltimo(promi);
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

	public String pesquisar(int data) {

		NoListaSimples noData = pesquisaHash(data);
		
		String concatData = "";

		if (noData != null) {
			while (noData != null && noData.getInfo() != null) {
				Promissoria info = noData.getInfo();

				concatData += UsoGeral.converterIntToString(info.getdataVenc()) + ";" + info.getNome() + ";"
						+ info.getCpf() + ";" + info.getValor() + ";" + info.getPaga() + ",";

				noData = noData.getNoDataRepetida();
			}
		}
		return concatData;
	}

	private NoListaSimples pesquisaHash(int data) {
		NoListaSimples aux;
		int pos = 0;
		pos = obterPosicaoHash(data);
		if (pos >= 0 && pos < this.tamanho) {
			if (this.vetorH[pos].getPrim() != null) {
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
