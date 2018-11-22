package Programa;

import Utils.SuporteArquivo;

public class Principal {
	// diretorio dos arquivos do programa
	private static String pathArq = System.getProperty("user.dir") + "\\ArquivosTrab\\";

	public static void main(String[] args) {

		// cria uma matriz pra armazenar os nomes e os tamanhos dos arquivos
		String[][] nomeArquivos = new String[15][2];
		nomeArquivos[0][0] = "promissoria500alea";
		nomeArquivos[0][1] = "500";
		nomeArquivos[1][0] = "promissoria500inv";
		nomeArquivos[1][1] = "500";
		nomeArquivos[2][0] = "promissoria500ord";
		nomeArquivos[2][1] = "500";
		nomeArquivos[3][0] = "promissoria1000alea";
		nomeArquivos[3][1] = "1000";
		nomeArquivos[4][0] = "promissoria1000inv";
		nomeArquivos[4][1] = "1000";
		nomeArquivos[5][0] = "promissoria1000ord";
		nomeArquivos[5][1] = "1000";
		nomeArquivos[6][0] = "promissoria5000alea";
		nomeArquivos[6][1] = "5000";
		nomeArquivos[7][0] = "promissoria5000inv";
		nomeArquivos[7][1] = "5000";
		nomeArquivos[8][0] = "promissoria5000ord";
		nomeArquivos[8][1] = "5000";
		nomeArquivos[9][0] = "promissoria10000alea";
		nomeArquivos[9][1] = "10000";
		nomeArquivos[10][0] = "promissoria10000inv";
		nomeArquivos[10][1] = "10000";
		nomeArquivos[11][0] = "promissoria10000ord";
		nomeArquivos[11][1] = "10000";
		nomeArquivos[12][0] = "promissoria30000alea";
		nomeArquivos[12][1] = "30000";
		nomeArquivos[13][0] = "promissoria30000inv";
		nomeArquivos[13][1] = "30000";
		nomeArquivos[14][0] = "promissoria30000ord";
		nomeArquivos[14][1] = "30000";

		// pego as datas para a memória, pois será usado até o fim do programa
		int[] datas = SuporteArquivo.obterDatasParaPesquisa(pathArq + "data.txt", 400);
	
		String nomeArquivo = "";
		int tamanhoArq = 0;
		for (int i = 0; i < nomeArquivos.length; i++) {

			// HEAPSORT + PESQUISA BINÁRIA
			nomeArquivo = nomeArquivos[i][0];
			tamanhoArq = Integer.parseInt(nomeArquivos[i][1]);
			ExecutarMetodos.executarHeapSort(nomeArquivo, tamanhoArq, datas, pathArq);

		}

		for (int i = 0; i < nomeArquivos.length; i++) {

			// QUICKSORT + PESQUISA BINÁRIA
			nomeArquivo = nomeArquivos[i][0];
			tamanhoArq = Integer.parseInt(nomeArquivos[i][1]);
			ExecutarMetodos.executarQuickSort(nomeArquivo, tamanhoArq, datas, pathArq);
		}

		for (int i = 0; i < nomeArquivos.length; i++) {

			// ARVORE BINÁRIA DE BUSCA
			nomeArquivo = nomeArquivos[i][0];
			tamanhoArq = Integer.parseInt(nomeArquivos[i][1]);
			ExecutarMetodos.executarAvoreBinaria(nomeArquivo, tamanhoArq, datas, pathArq);
		}

		for (int i = 0; i < nomeArquivos.length; i++) {

			// ARVORE AVL
			nomeArquivo = nomeArquivos[i][0];
			tamanhoArq = Integer.parseInt(nomeArquivos[i][1]);
			ExecutarMetodos.executarAvoreAVL(nomeArquivo, tamanhoArq, datas, pathArq);
		}

		for (int i = 0; i < nomeArquivos.length; i++) {

			// HASHING COM ENCADEAMENTO
			nomeArquivo = nomeArquivos[i][0];
			tamanhoArq = Integer.parseInt(nomeArquivos[i][1]);
			ExecutarMetodos.executarHashingEncadeado(nomeArquivo, tamanhoArq, datas, pathArq);
		}

		ExecutarMetodos.gravarTempos(pathArq);
	}

}