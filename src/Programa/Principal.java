<<<<<<< HEAD
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
=======
package Programa;

import Objetos.Promissoria;
import Utils.SuporteArquivo;

public class Principal {
	private static String path = System.getProperty("user.dir") + "\\ArquivosTrab\\promissoria30000alea.txt";

	public static void main(String[] args) {

		// teste leitura arquivo
		Promissoria[] p = SuporteArquivo.leArquivo(path, 30000);

		// teste imprimindo resultado
		for (int i = 0; i < p.length; i++) {
			System.out.println(p[i].toString());
		}
	}

}
>>>>>>> 36fdd713f065e8dbc0d72e0d2a389d7ebcd5c957
