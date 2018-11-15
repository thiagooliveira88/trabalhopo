package Programa;

import java.util.LinkedHashMap;
import Metodos.ABB;
import Metodos.ArvoreAVL;
import Metodos.HashingEncadeado;
import Metodos.Heapsort;
import Metodos.QuickSort;
import Objetos.NoArv;
import Objetos.Promissoria;
import Utils.SuporteArquivo;
import Utils.UsoGeral;

public class ExecutaMetodos {

	public static void executarHeapSort(LinkedHashMap<String, Integer> nomeArqs, int[] datas, String arquivo, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// faço o processo 5 vezes
		// HeapSort + Pesquisa binária
		for (int i = 0; i < 4; i++) {

			// leio o arquivo e atribuo o resultado para o vetor de
			// promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
			// executo o método HeapSort
			Heapsort.executarHeapSort(vetPromissoria);
			// defino o caminho e nome do arquivo que será salvo em disco.
			String caminhoArq = pathArq + "ArquivosGerados\\HeapSort" + arquivo + ".txt";
			// escrevo no arquivo
			SuporteArquivo.escreverArquivo(vetPromissoria, caminhoArq);
			// realizo a pesquisa para cada data e adiciono em um vetor os
			// indices
			// encontrados
			int[] vetIndices = new int[datas.length];
			for (int j = 0; j < datas.length; j++) {
				vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vetPromissoria);
			}
			String caminhoResultado = pathArq + "ArquivosGerados\\HeapSortResultadoPesquisa" + arquivo
					+ ".txt";
			SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
		}

		fimExec = System.currentTimeMillis();

		System.out.println("HeapSort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void executarQuickSort(LinkedHashMap<String, Integer> nomeArqs, int[] datas, String arquivo, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// QuickSort + Pesquisa binária
		for (int i = 0; i < 4; i++) {

			// leio o arquivo e atribuo o resultado para o vetor de
			// promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
			// executo o método QuickSort
			QuickSort.executarQuickSort(vetPromissoria);
			// defino o caminho e nome do arquivo que será salvo em disco.
			String caminhoArq = pathArq + "ArquivosGerados\\Quicksort" + arquivo + ".txt";
			// escrevo no arquivo
			SuporteArquivo.escreverArquivo(vetPromissoria, caminhoArq);
			// realizo a pesquisa para cada data e adiciono em um vetor os
			// indices
			// encontrados
			int[] vetIndices = new int[datas.length];
			for (int j = 0; j < datas.length; j++) {
				vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vetPromissoria);
			}
			String caminhoResultado = pathArq + "ArquivosGerados\\QuicksortResultadoPesquisa" + arquivo
					+ ".txt";
			SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
		}

		fimExec = System.currentTimeMillis();
		System.out.println("Quicksort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}
	
	public static void executarAvoreBinaria(LinkedHashMap<String, Integer> nomeArqs, int[] datas, String arquivo, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// ArvoreBinaria
		// leio o arquivo e atribuo o resultado para o vetor de promissoria
		vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
		ABB arvoreBinaria = null;
		for (int i = 0; i < 4; i++) {
			arvoreBinaria = new ABB();
			// insiro os registros no método ArvoreBinaria
			for (int j = 0; j < vetPromissoria.length; j++) {
				arvoreBinaria.inserir(vetPromissoria[j]);
			}

			// ordeno a ArvoreBinaria
			NoArv[] vetOrdenado = arvoreBinaria.CamCentral(nomeArqs.get(arquivo));

			// balancear a ArvoreBinaria
			arvoreBinaria.ArvoreBalanceada(vetOrdenado);

			String caminhoResultado = pathArq + "ArquivosGerados\\ArvoreBinariaResultadoPesquisa"
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
	}

	public static void executarAvoreAVL(LinkedHashMap<String, Integer> nomeArqs, int[] datas, String arquivo, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
//			// ARVORE AVL
		int tamanhoVet = nomeArqs.get(arquivo);
		vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoVet);

		for (int i = 0; i < 4; i++) {

			ArvoreAVL arvoreAVL = new ArvoreAVL();

			// insiro os registros no método ARVORE AVL
			for (int j = 0; j < vetPromissoria.length; j++) {
				arvoreAVL.insereRaiz(vetPromissoria[j]);
			}

			String caminhoResultado = pathArq + "ArquivosGerados\\ArvoreAVLResultadoPesquisa" + arquivo
					+ ".txt";

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
	}
	
	public static void executarHashingEncadeado(LinkedHashMap<String, Integer> nomeArqs, int[] datas, String arquivo, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
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

			String caminhoResultado =  pathArq + "ArquivosGerados\\HashingResultadoPesquisa" + arquivo
					+ ".txt";
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
