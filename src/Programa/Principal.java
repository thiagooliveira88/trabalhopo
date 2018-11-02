package Programa;

import java.sql.Date;
import java.util.LinkedHashMap;
import Metodos.Heapsort;
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
		nomeArqs.put("promissoria500alea.txt", 500);
		nomeArqs.put("promissoria500inv.txt", 500);
		nomeArqs.put("promissoria500ord.txt", 500);
		nomeArqs.put("promissoria1000alea.txt", 1000);
		nomeArqs.put("promissoria1000inv.txt", 1000);
		nomeArqs.put("promissoria1000ord.txt", 1000);
		nomeArqs.put("promissoria5000alea.txt", 5000);
		nomeArqs.put("promissoria5000inv.txt", 5000);
		nomeArqs.put("promissoria5000ord.txt", 5000);
		nomeArqs.put("promissoria10000alea.txt", 10000);
		nomeArqs.put("promissoria10000inv.txt", 10000);
		nomeArqs.put("promissoria10000ord.txt", 10000);
		nomeArqs.put("promissoria30000alea.txt", 30000);
		nomeArqs.put("promissoria30000inv.txt", 30000);
		nomeArqs.put("promissoria30000ord.txt", 30000);

		Promissoria[] vet = null;
		Date[] datas = null;
		// pego as datas para a memória, pois será usado até o fim do programa
		datas = SuporteArquivo.obterDatasParaPesquisa(pathArq + "data.txt", 400);
		/*
		 * percorro todo o LinkedHashMap(Dictonary) o método Keyset retorna as chaves de
		 * pesquisa, que no caso são os nomes dos arquivos, assim consigo pegar o nome
		 * do arquivo e o tamanho do vetor.
		 */
		for (String arquivo : nomeArqs.keySet()) {

			// leio o arquivo e atribuo o resultado para o vetor de promissoria
			vet = SuporteArquivo.leArquivo(pathArq + arquivo, nomeArqs.get(arquivo));

			// executo o método HeapSort
			Heapsort.executarHeapSort(vet);
			// defino o caminho e nome do arquivo que será salvo em disco.
			String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\HeapSort" + arquivo;
			// escrevo no arquivo
			SuporteArquivo.escreverArquivo(vet, caminhoArq);

			// realizo a pesquisa para cada data e adiciono em um vetor os indices
			// encontrados
			for (int i = 0; i < datas.length; i++) {
				UsoGeral.pesquisaBinaria(datas[i], vet);
			}

		}
	}

}