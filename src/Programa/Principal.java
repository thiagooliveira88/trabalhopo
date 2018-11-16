package Programa;

import java.util.LinkedHashMap;
import Utils.SuporteArquivo;

public class Principal {
	// diretorio dos arquivos do programa
	private static String pathArq = System.getProperty("user.dir") + "\\ArquivosTrab\\";

	public static void main(String[] args) {

		// crio um LinkedHashMap com o nome do arquivo a ser lido e com o
		// tamanho do vetor.
		LinkedHashMap<String, Integer> nomeArqs = new LinkedHashMap<String, Integer>();
		nomeArqs.put("promissoria500alea", 500);
		nomeArqs.put("promissoria500inv", 500);
		nomeArqs.put("promissoria500ord", 500);
		nomeArqs.put("promissoria1000alea", 1000);
		nomeArqs.put("promissoria1000inv", 1000);
		nomeArqs.put("promissoria1000ord", 1000);
		nomeArqs.put("promissoria5000alea", 5000);
		nomeArqs.put("promissoria5000inv", 5000);
		nomeArqs.put("promissoria5000ord", 5000);
		nomeArqs.put("promissoria10000alea", 10000);
		nomeArqs.put("promissoria10000inv", 10000);
		nomeArqs.put("promissoria10000ord", 10000);
		nomeArqs.put("promissoria30000alea", 30000);
		nomeArqs.put("promissoria30000inv", 30000);
		nomeArqs.put("promissoria30000ord", 30000);

		// pego as datas para a mem�ria, pois ser� usado at� o fim do programa
		int[] datas = SuporteArquivo.obterDatasParaPesquisa(pathArq + "data.txt", 400);
		/*
		 * percorro todo o LinkedHashMap(Dictonary) o m�todo Keyset retorna as chaves de
		 * pesquisa, que no caso s�o os nomes dos arquivos, assim consigo pegar o nome
		 * do arquivo e o tamanho do vetor.
		 */
		for (String arquivo : nomeArqs.keySet()) {

			// HEAPSORT + PESQUISA BIN�RIA
			ExecutarMetodos.executarHeapSort(nomeArqs, datas, arquivo, pathArq);
		}
		for (String arquivo : nomeArqs.keySet()) {

			// QUICKSORT + PESQUISA BIN�RIA
			ExecutarMetodos.executarQuickSort(nomeArqs, datas, arquivo, pathArq);
		}
		for (String arquivo : nomeArqs.keySet()) {

			// ARVORE BIN�RIA DE BUSCA
			ExecutarMetodos.executarAvoreBinaria(nomeArqs, datas, arquivo, pathArq);
		}
		for (String arquivo : nomeArqs.keySet()) {

			// ARVORE AVL
			ExecutarMetodos.executarAvoreAVL(nomeArqs, datas, arquivo, pathArq);
		}
		for (String arquivo : nomeArqs.keySet()) {

			// HASHING COM ENCADEAMENTO
			ExecutarMetodos.executarHashingEncadeado(nomeArqs, datas, arquivo, pathArq);
		}
		
		ExecutarMetodos.gravarTempos(pathArq);
	}

}