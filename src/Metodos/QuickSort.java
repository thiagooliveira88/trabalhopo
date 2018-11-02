package Metodos;

import Objetos.Promissoria;

public class QuickSort {
	private static Promissoria[] vetor;
	private static int nElem;

	public static void executarQuickSort(Promissoria[] vet) {
		vetor = vet;
		nElem = vet.length - 1;

		ordena(0, nElem);
	}

	private static void ordena(int esq, int dir) {
		int i = esq, j = dir;

		Promissoria pivo = null;
		Promissoria temp;

		pivo = vetor[(i + j) / 2];

		do {
			while (vetor[i].getdataVenc().compareTo(pivo.getdataVenc()) < 0)
				i++;
			while (vetor[j].getdataVenc().compareTo(pivo.getdataVenc()) > 0)
				j--;
			if (i <= j) {
				temp = vetor[i];
				vetor[i] = vetor[j];
				vetor[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);
		if (esq < j)
			ordena(esq, j);
		if (dir > i)
			ordena(i, dir);
	}

}
