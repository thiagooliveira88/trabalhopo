package Programa;

import Metodos.ABB;
import Metodos.ArvoreAVL;
import Metodos.HashingEncadeado;
import Metodos.Heapsort;
import Metodos.QuickSort;
import Objetos.NoArv;
import Objetos.NoListaSimples;
import Objetos.NodoAVL;
import Objetos.Promissoria;
import Utils.SuporteArquivo;
import Utils.UsoGeral;

public class ExecutarMetodos {

	private static String[] tempos = new String[75];
	private static int indiceTempos = 0;

	public static void executarHeapSort(String arquivo, int tamanhoArq, int[] datas, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// faço o processo 5 vezes
		// HeapSort + Pesquisa binária
		for (int i = 0; i < 5; i++) {

			// leio o arquivo e atribuo o resultado para o vetor de
			// promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoArq);
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
			String caminhoResultado = pathArq + "ArquivosGerados\\HeapSortResultadoPesquisa" + arquivo + ".txt";
			SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
		}

		fimExec = System.currentTimeMillis();

		tempos[indiceTempos] = "HeapSort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos";
		indiceTempos++;
		System.out.println("HeapSort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void executarQuickSort(String arquivo, int tamanhoArq, int[] datas, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// QuickSort + Pesquisa binária
		for (int i = 0; i < 5; i++) {

			// leio o arquivo e atribuo o resultado para o vetor de
			// promissoria
			vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoArq);
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
			String caminhoResultado = pathArq + "ArquivosGerados\\QuicksortResultadoPesquisa" + arquivo + ".txt";
			SuporteArquivo.escreverResultadoPesquisa(vetIndices, datas, vetPromissoria, caminhoResultado);
		}

		fimExec = System.currentTimeMillis();
		tempos[indiceTempos] = "Quicksort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos";
		indiceTempos++;
		System.out.println("Quicksort " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void executarAvoreBinaria(String arquivo, int tamanhoArq, int[] datas, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// ArvoreBinaria
		// leio o arquivo e atribuo o resultado para o vetor de promissoria
		vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoArq);
		ABB arvoreBinaria = null;
		for (int i = 0; i < 5; i++) {
			arvoreBinaria = new ABB();
			// insiro os registros no método ArvoreBinaria
			for (int j = 0; j < vetPromissoria.length; j++) {
				arvoreBinaria.inserir(vetPromissoria[j]);
			}

			// ordeno a ArvoreBinaria
			NoArv[] vetOrdenado = arvoreBinaria.CamCentral(tamanhoArq);

			// balancear a ArvoreBinaria
			arvoreBinaria = arvoreBinaria.ArvoreBalanceada(vetOrdenado);

			String caminhoResultado = pathArq + "ArquivosGerados\\ArvoreBinariaResultadoPesquisa" + arquivo + ".txt";

			Object[] resultadoPesquisa = new Object[datas.length];
			for (int j = 0; j < datas.length; j++) {

				Object retornoPesquisa = arvoreBinaria.pesquisarData(datas[j]);
				if (retornoPesquisa.getClass() == NoArv.class) {
					NoArv info = (NoArv) retornoPesquisa;
					NoArv aux = info;

					while (aux != null && aux.getInfo() != null) {
						resultadoPesquisa[j] = aux.getInfo();
						aux = aux.getNoRepetido();
						j++;
					}
					j--;
				} else {
					resultadoPesquisa[j] = new Promissoria(retornoPesquisa.toString(), null, null, "0", "false");
				}

			}

			SuporteArquivo.escreverResultadoPesquisa(resultadoPesquisa, caminhoResultado);

		}

		fimExec = System.currentTimeMillis();
		tempos[indiceTempos] = "ArvoreBinaria " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos";
		indiceTempos++;
		System.out.println("ArvoreBinaria " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void executarAvoreAVL(String arquivo, int tamanhoArq, int[] datas, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// ARVORE AVL
		vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoArq);

		for (int i = 0; i < 5; i++) {

			ArvoreAVL arvoreAVL = new ArvoreAVL();

			// insiro os registros no método ARVORE AVL
			for (int j = 0; j < vetPromissoria.length; j++) {
				arvoreAVL.insereRaiz(vetPromissoria[j]);
			}

			String caminhoResultado = pathArq + "ArquivosGerados\\ArvoreAVLResultadoPesquisa" + arquivo + ".txt";

			Object[] resultadoPesquisa = new Object[datas.length];
			for (int j = 0; j < datas.length; j++) {

				Object retornoPesquisa = arvoreAVL.pesquisar(datas[j]);
				if (retornoPesquisa.getClass() == NodoAVL.class) {
					NodoAVL info = (NodoAVL) retornoPesquisa;
					NodoAVL aux = info;

					while (aux != null && aux.getInfo() != null) {
						resultadoPesquisa[j] = aux.getInfo();
						aux = aux.getNoRepetido();
						j++;
					}
					j--;
				} else {
					resultadoPesquisa[j] = new Promissoria(retornoPesquisa.toString(), null, null, "0", "false");
				}

			}

			SuporteArquivo.escreverResultadoPesquisa(resultadoPesquisa, caminhoResultado);
		}
		fimExec = System.currentTimeMillis();
		tempos[indiceTempos] = "ArvoreAVL " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos";
		indiceTempos++;
		System.out.println("ArvoreAVL " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void executarHashingEncadeado(String arquivo, int tamanhoArq, int[] datas, String pathArq) {
		Promissoria[] vetPromissoria;
		long inicioExec;
		long fimExec;
		inicioExec = System.currentTimeMillis();
		// Hashing com encadeamento
		HashingEncadeado hashingEncad = null;

		// leio o arquivo e atribuo o resultado para o vetor de promissoria
		vetPromissoria = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", tamanhoArq);

		for (int i = 0; i < 5; i++) {
			hashingEncad = new HashingEncadeado(tamanhoArq);

			// insiro os registros
			for (int j = 0; j < vetPromissoria.length; j++) {
				hashingEncad.inserir(vetPromissoria[j]);
			}

			String caminhoResultado = pathArq + "ArquivosGerados\\HashingResultadoPesquisa" + arquivo + ".txt";

			Object[] resultadoPesquisa = new Object[datas.length];
			for (int j = 0; j < datas.length; j++) {

				Object retornoPesquisa = hashingEncad.pesquisar(datas[j]);
				if (retornoPesquisa.getClass() == NoListaSimples.class) {
					NoListaSimples info = (NoListaSimples) retornoPesquisa;
					NoListaSimples aux = info;

					while (aux != null && aux.getInfo() != null) {
						resultadoPesquisa[j] = aux.getInfo();
						aux = aux.getNoDataRepetida();
						j++;
					}
					j--;
				} else {
					resultadoPesquisa[j] = new Promissoria(retornoPesquisa.toString(), null, null, "0", "false");
				}

			}

			SuporteArquivo.escreverResultadoPesquisa(resultadoPesquisa, caminhoResultado);
		}

		fimExec = System.currentTimeMillis();
		tempos[indiceTempos] = "HashingEncadeado " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos";
		indiceTempos++;
		System.out.println("HashingEncadeado " + arquivo + " = " + ((fimExec - inicioExec) / 5) + " milissegundos");
	}

	public static void gravarTempos(String pathArq) {
		String caminhoResultado = pathArq + "ArquivosGerados\\_ArquivoTempos.txt";
		SuporteArquivo.escreverTempos(tempos, caminhoResultado);
	}
}
