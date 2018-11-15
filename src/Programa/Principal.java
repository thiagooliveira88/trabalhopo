package Programa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import Metodos.ABB;
import Metodos.ArvoreAVL;
import Metodos.HashingEncadeado;
import Metodos.Heapsort;
import Metodos.NoArv;
import Metodos.NoListaSimples;
import Metodos.QuickSort;
import Metodos.UsoGeral;
import Objetos.Promissoria;
import Utils.SuporteArquivo;

public class Principal {
	private static String pathAPP = System.getProperty("user.dir");
	private static String pathArq = pathAPP + "\\ArquivosTrab\\";

	public static void main(String[] args) {

		// crio um LinkedHashMap com o nome do arquivo a ser lido e com o
		// tamanho do
		// vetor.
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

		// int valor = SuporteArquivo.converterStringToInt("13/11/2018");
		Promissoria[] vetPromissoria = null;
		int[] datas = null;
		long inicioExec = 0, fimExec = 0;
		// pego as datas para a mem�ria, pois ser� usado at� o fim do programa
		datas = SuporteArquivo.obterDatasParaPesquisa(pathArq + "data.txt", 400);
		/*
		 * percorro todo o LinkedHashMap(Dictonary) o m�todo Keyset retorna as
		 * chaves de pesquisa, que no caso s�o os nomes dos arquivos, assim
		 * consigo pegar o nome do arquivo e o tamanho do vetor.
		 */
		for (String arquivo : nomeArqs.keySet()) {

			// =================HEAPSORT + PESQUISA BIN�RIA================
			inicioExec = System.currentTimeMillis();
			// fa�o o processo 5 vezes
			// HeapSort + Pesquisa bin�ria
			for (int i = 0; i < 1; i++) {

				// leio o arquivo e atribuo o resultado para o vetor de
				// promissoria
				vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
				// executo o m�todo HeapSort
				Heapsort.executarHeapSort(vetPromissoria);
				// defino o caminho e nome do arquivo que ser� salvo em disco.
				String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HeapSort" + arquivo + ".txt";
				// escrevo no arquivo
				SuporteArquivo.escreverArquivo(vetPromissoria, caminhoArq);
				// realizo a pesquisa para cada data e adiciono em um vetor os
				// indices
				// encontrados
				int[] vetIndices = new int[datas.length];
				for (int j = 0; j < datas.length; j++) {
					vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vetPromissoria);
				}
				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HeapSortResultadoPesquisa"
						+ arquivo + ".txt";
				SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
			}

			fimExec = System.currentTimeMillis();

			System.out.println("HeapSort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");

			// =========QUICKSORT + PESQUISA
			// BIN�RIA==========================

			inicioExec = System.currentTimeMillis();
			// QuickSort + Pesquisa bin�ria
			for (int i = 0; i < 4; i++) {

				// leio o arquivo e atribuo o resultado para o vetor de
				// promissoria
				vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
				// executo o m�todo QuickSort
				QuickSort.executarQuickSort(vetPromissoria);
				// defino o caminho e nome do arquivo que ser� salvo em disco.
				String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\Quicksort" + arquivo + ".txt";
				// escrevo no arquivo
				SuporteArquivo.escreverArquivo(vetPromissoria, caminhoArq);
				// realizo a pesquisa para cada data e adiciono em um vetor os
				// indices
				// encontrados
				int[] vetIndices = new int[datas.length];
				for (int j = 0; j < datas.length; j++) {
					vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vetPromissoria);
				}
				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\QuicksortResultadoPesquisa"
						+ arquivo + ".txt";
				SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
			}

			fimExec = System.currentTimeMillis();
			System.out.println("Quicksort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");

			// =====ARVORE BIN�RIA DE
			// BUSCA===============================================

			inicioExec = System.currentTimeMillis();
			// ArvoreBinaria
			// leio o arquivo e atribuo o resultado para o vetor de promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
			ABB arvoreBinaria = null;
			for (int i = 0; i < 4; i++) {
				arvoreBinaria = new ABB();
				// insiro os registros no m�todo ArvoreBinaria
				for (int j = 0; j < vetPromissoria.length; j++) {
					arvoreBinaria.inserir(vetPromissoria[j]);
				}

				// ordeno a ArvoreBinaria
				NoArv[] vetOrdenado = arvoreBinaria.CamCentral(nomeArqs.get(arquivo));

				// balancear a ArvoreBinaria
				arvoreBinaria.ArvoreBalanceada(vetOrdenado);

				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\ArvoreBinariaResultadoPesquisa"
						+ arquivo + ".txt";

				String resultadoPesquisa = "";
				String datasEncontradas = "";
				String datasNaoEncontradas = "";
				// realizo a pesquisa para cada data
				for (int j = 0; j < datas.length; j++) {
					resultadoPesquisa = arvoreBinaria.obterPesquisaString(datas[j]);
					if (resultadoPesquisa != "") {
						datasEncontradas += resultadoPesquisa;
					} else {

						datasNaoEncontradas += UsoGeral.converterIntToString(datas[j]) + "\n";
					}
				}

				SuporteArquivo.escreverResultadoPesquisa(datasEncontradas, datasNaoEncontradas, caminhoResultado);
			}

			fimExec = System.currentTimeMillis();
			System.out.println("ArvoreBinaria " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");

//			// ======================================ARVORE
//			// AVL===============================================
//
			inicioExec = System.currentTimeMillis();
//			// ARVORE AVL
			int tamanhoVet = nomeArqs.get(arquivo);
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoVet);

			for (int i = 0; i < 1; i++) {

				ArvoreAVL arvoreAVL = new ArvoreAVL();

				// insiro os registros no m�todo ARVORE AVL
				for (int j = 0; j < vetPromissoria.length; j++) {
					arvoreAVL.insereRaiz(vetPromissoria[j]);
				}

				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\ArvoreAVLResultadoPesquisa"
						+ arquivo + ".txt";

				String resultadoPesquisa = "";
				String datasEncontradas = "";
				String datasNaoEncontradas = "";
				// realizo a pesquisa para cada data
				for (int j = 0; j < datas.length; j++) {
					resultadoPesquisa = arvoreAVL.obterPesquisaString(datas[j]);
					if (resultadoPesquisa != "") {
						datasEncontradas += resultadoPesquisa;
					} else {

						datasNaoEncontradas += UsoGeral.converterIntToString(datas[j]) + "\n";
					}
				}

				SuporteArquivo.escreverResultadoPesquisa(datasEncontradas, datasNaoEncontradas, caminhoResultado);
			}
			fimExec = System.currentTimeMillis();
			System.out.println("ArvoreAVL " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");

			// =====================HASHING COM ENCADEAMENTO=============

			inicioExec = System.currentTimeMillis();
			// Hashing com encadeamento
			HashingEncadeado hashingEncad = null;

			// leio o arquivo e atribuo o resultado para o vetor de promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));

			for (int i = 0; i < 4; i++) {
				hashingEncad = new HashingEncadeado(nomeArqs.get(arquivo));

				// insiro os registros
				for (int j = 0; j < vetPromissoria.length; j++) {
					hashingEncad.inserir(vetPromissoria[j]);
				}

				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HashingResultadoPesquisa"
						+ arquivo + ".txt";
				String resultadoPesquisa = "";
				String datasEncontradas = "";
				String datasNaoEncontradas = "";
				// realizo a pesquisa para cada data
				for (int j = 0; j < datas.length; j++) {
					resultadoPesquisa = hashingEncad.pesquisar(datas[j]);
					if (resultadoPesquisa != "") {
						datasEncontradas += resultadoPesquisa;
					} else {

						datasNaoEncontradas += UsoGeral.converterIntToString(datas[j]) + "\n";
					}
				}

				SuporteArquivo.escreverResultadoPesquisa(datasEncontradas, datasNaoEncontradas, caminhoResultado);
			}

			fimExec = System.currentTimeMillis();
			System.out.println("HashingEncadeado " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
		}
	}

}