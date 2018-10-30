package Programa;

import Metodos.Heapsort;
import Metodos.UsoGeral;
import Objetos.Promissoria;
import Utils.SuporteArquivo;

public class Principal {
	private static String pathAPP = System.getProperty("user.dir");
	private static String pathArq = pathAPP + "\\ArquivosTrab\\promissoria30000alea.txt";

	public static void main(String[] args) {

		// teste leitura arquivo
		Promissoria[] p = SuporteArquivo.leArquivo(pathArq, 30000);

		String caminhoArq = pathAPP + "\\ArquivosTrab\\ArquivosGerados\\teste.txt";

		Heapsort.executaHeapSort(p);
		
		SuporteArquivo.escreverArquivo(p, caminhoArq);
		
		//falta pesquisar no arquivo e escrever o resultado da pesquisa.
		

	}

}
