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
