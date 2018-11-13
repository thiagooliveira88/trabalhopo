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

	public void inserir(Promissoria promi) {

		// pesquisa a data
		NoListaSimples noNovo = pesquisaHash(promi.getdataVenc());
		if (noNovo != null) {

			NoListaSimples temp = noNovo;
			while (temp.getNoDataRepetida() != null) {
				temp = temp.getNoDataRepetida();
			}
			// se chegou até aqui, quer dizer que o NoRepetido esta nulo
			// insiro o elemento repetido.
			temp.setNoDataRepetida(new NoListaSimples(promi));
			;
		} else {

			int pos = 0;
			pos = obterPosicaoHash(promi.getdataVenc());
			this.getVetorH()[pos].inserirUltimo(promi);
		}

	}

	private int obterPosicaoHash(Date data) {
		int mod = 0;
		int soma = 0;
		char carac;
		for (int i = 0; i < data.toString().length(); i++) {

			carac = data.toString().charAt(i);
			soma += Character.getNumericValue(carac);
		}

		switch (this.tamanho) {
		case 550:
			mod = (soma % 557);
			break;
		case 1100:
			mod = (soma % 1103);
			break;
		case 5500:
			mod = (soma % 5503);
			break;
		case 11000:
			mod = (soma % 11003);
			break;
		case 33000:
			mod = (soma % 33013);
			break;
		}
		return mod;
	}

	public String pesquisar(Date data) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		NoListaSimples noData = pesquisaHash(data);
		String concatData = "";
		String separadorSplit = ",";

		if (noData != null) {
			while (noData != null && noData.getInfo() != null) {
				Promissoria info = noData.getInfo();

				concatData += formatter.format(info.getdataVenc()) + ";" + info.getNome() + ";" + info.getCpf() + ";"
						+ info.getValor() + ";" + info.getPaga() + separadorSplit;

				noData = noData.getNoDataRepetida();
			}
		}
		return concatData;
	}

	private NoListaSimples pesquisaHash(Date data) {
		NoListaSimples aux;
		int pos = 0;
		pos = obterPosicaoHash(data);
		if (pos >= 0 && pos < this.tamanho) {
			if (this.vetorH[pos].getPrim() != null) {
				aux = this.vetorH[pos].getPrim();
				while (aux != null) {

					if (aux.getInfo().getdataVenc().compareTo(data) == 0) {
						return aux;
					} else {
						aux = aux.getProx();
					}
				}
			}
		}
		return null;
	}

	public static void escreverResultadoPesquisa(String datasEncontradas, String datasNaoEncontradas,
			String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			String[] promissoria = datasEncontradas.split(",");
			Double totalNaoPago = 0D;

			String pagas = "";
			String naoPagas = "";
			String verificaData = null;
			// percorro todo o vetor
			for (int j = 0; j < promissoria.length; j++) {

				String[] dados = promissoria[j].split(";");
				/*
				 * data = dados[0] nome = dados[1] cpf = dados[2] valor= dados[3] paga =
				 * dados[4]
				 */

				if (verificaData != null && verificaData.equals(dados[0])) {

					if (Boolean.parseBoolean(dados[4])) {
						pagas += dados[0] + ";" + dados[3] + ";" + dados[1] + "\n";
					}

					if (!Boolean.parseBoolean(dados[4])) {

						naoPagas += dados[0] + ";" + dados[3] + ";" + dados[1] + "\n";
						totalNaoPago += Double.parseDouble(dados[3]);
					}
				} else {
					if (pagas != "" || naoPagas != "") {
						gravarArquivo.println("PAGAS:");
						gravarArquivo.println(pagas);
						gravarArquivo.println("NÃO PAGAS:");
						gravarArquivo.println(naoPagas);
						gravarArquivo.println("TOTAL NÃO PAGA: " + totalNaoPago);
						gravarArquivo
								.println("========================================================================");

						pagas = "";
						naoPagas = "";
						totalNaoPago = 0D;
					}
					if (Boolean.parseBoolean(dados[4])) {
						pagas += dados[0] + ";" + dados[3] + ";" + dados[1] + "\n";
					}

					if (!Boolean.parseBoolean(dados[4])) {

						naoPagas += dados[0] + ";" + dados[3] + ";" + dados[1] + "\n";
						totalNaoPago += Double.parseDouble(dados[3]);
					}

				}

				verificaData = dados[0];

			}
			// grava as datas não encontradas
			gravarArquivo.println("NÃO HÁ PROMISSÓRIAS NAS DATAS MENCIONADAS:" + "\n" + datasNaoEncontradas);
			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
