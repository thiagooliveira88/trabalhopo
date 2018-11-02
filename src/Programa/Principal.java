package Programa;

import java.util.Date;
import java.util.LinkedHashMap;

import Metodos.ABB;
import Metodos.Heapsort;
import Metodos.QuickSort;
import Metodos.UsoGeral;
import Objetos.Promissoria;
import Utils.SuporteArquivo;

public class Principal {
	private static String pathAPP = System.getProperty("user.dir");
	private static String pathArq = pathAPP + "\\ArquivosTrab\\";

	public static void main(String[] args) {

		// crio um LinkedHashMap com o nome do arquivo a ser lido e com o tamanho do
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

		Promissoria[] vet = null;
		Date[] datas = null;
		long inicioProcesso = 0, fimProcesso = 0;
		// pego as datas para a memória, pois será usado até o fim do programa
		datas = SuporteArquivo.obterDatasParaPesquisa(pathArq + "data.txt", 400);
		/*
		 * percorro todo o LinkedHashMap(Dictonary) o método Keyset retorna as chaves de
		 * pesquisa, que no caso são os nomes dos arquivos, assim consigo pegar o nome
		 * do arquivo e o tamanho do vetor.
		 */
		for (String arquivo : nomeArqs.keySet()) {

			//======================================HEAPSORT + PESQUISA BINÁRIA===============================================
			inicioProcesso = System.currentTimeMillis();
			// faço o processo 5 vezes 
			// HeapSort + Pesquisa binária
			for (int i = 0; i < 4; i++) {

				// leio o arquivo e atribuo o resultado para o vetor de promissoria
				vet = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
				// executo o método HeapSort
				Heapsort.executarHeapSort(vet);
				// defino o caminho e nome do arquivo que será salvo em disco.
				String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HeapSort" + arquivo + ".txt";
				// escrevo no arquivo
				SuporteArquivo.escreverArquivo(vet, caminhoArq);
				// realizo a pesquisa para cada data e adiciono em um vetor os indices
				// encontrados
				int[] vetIndices = new int[datas.length];
				for (int j = 0; j < datas.length; j++) {
					vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vet);
				}
				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HeapSortResultadoPesquisa"
						+ arquivo + ".txt";
				UsoGeral.escreverResultadoPesquisa(vetIndices, vet, caminhoResultado);
			}

			fimProcesso = System.currentTimeMillis();

			System.out.println("HeapSort " + arquivo + " = " + ((fimProcesso - inicioProcesso) / 5 ) + " milissegundos");
		
			
			//======================================QUICKSORT + PESQUISA BINÁRIA===============================================	
			
			inicioProcesso = System.currentTimeMillis();			
			// QuickSort + Pesquisa binária
			for (int i = 0; i < 4; i++) {

				// leio o arquivo e atribuo o resultado para o vetor de promissoria
				vet = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
				// executo o método QuickSort
				QuickSort.executarQuickSort(vet);
				// defino o caminho e nome do arquivo que será salvo em disco.
				String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\Quicksort" + arquivo + ".txt";
				// escrevo no arquivo
				SuporteArquivo.escreverArquivo(vet, caminhoArq);
				// realizo a pesquisa para cada data e adiciono em um vetor os indices
				// encontrados
				int[] vetIndices = new int[datas.length];
				for (int j = 0; j < datas.length; j++) {
					vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vet);
				}
				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\QuicksortResultadoPesquisa"
						+ arquivo + ".txt";
				UsoGeral.escreverResultadoPesquisa(vetIndices, vet, caminhoResultado);
			}

			fimProcesso = System.currentTimeMillis();			
			System.out.println("Quicksort " + arquivo + " = " + ((fimProcesso - inicioProcesso) / 5 ) + " milissegundos");
			
			
			//======================================ARVORE BINÁRIA DE BUSCA===============================================	
			
			inicioProcesso = System.currentTimeMillis();			
			// ABB
			for (int i = 0; i < 4; i++) {

				// leio o arquivo e atribuo o resultado para o vetor de promissoria
				vet = SuporteArquivo.leArquivo(pathArq + arquivo + ".txt", nomeArqs.get(arquivo));
				// insiro os registros no método ABB
				for (int j = 0; j < vet.length; j++) {
					ABB.inserir(vet[i]);
				}
				
				//ordeno a ABB
				vet = ABB.CamCentral();
				
				// defino o caminho e nome do arquivo que será salvo em disco.
				String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\ABB" + arquivo + ".txt";
				// escrevo no arquivo
				SuporteArquivo.escreverArquivo(vet, caminhoArq);
				// realizo a pesquisa para cada data e adiciono em um vetor os indices
				// encontrados
				int[] vetIndices = new int[datas.length];
				for (int j = 0; j < datas.length; j++) {
					vetIndices[j] = UsoGeral.pesquisaBinaria(datas[j], vet);
				}
				String caminhoResultado = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\ABBResultadoPesquisa"
						+ arquivo + ".txt";
				UsoGeral.escreverResultadoPesquisa(vetIndices, vet, caminhoResultado);
			}

			fimProcesso = System.currentTimeMillis();			
			System.out.println("ABB " + arquivo + " = " + ((fimProcesso - inicioProcesso) / 5 ) + " milissegundos");
		}
	}

}