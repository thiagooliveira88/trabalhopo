package Metodos;

import java.util.Date;
import Objetos.Promissoria;

public class UsoGeral {
	
	public static int pesquisaBinaria(Date dataVenc, Promissoria[] pro) {
		int meio, esq, dir;
		esq = 0;
		dir = pro.length - 1;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (dataVenc.compareTo(pro[meio].getdataVenc()) == 0)
				return meio;
			else {
				if (dataVenc.compareTo(pro[meio].getdataVenc()) < 0)
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return -1;
	}
}
