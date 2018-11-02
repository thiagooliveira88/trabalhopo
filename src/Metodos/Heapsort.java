package Metodos;

import Objetos.Promissoria;

public class Heapsort {
	private static int nElem;
	private static Promissoria[] vetor;
	
	public static void executarHeapSort(Promissoria[] pro) {
		vetor = pro;
		nElem = pro.length - 1;
		
		int dir = nElem;
		int esq = (dir - 1) / 2;
		Promissoria temp;

		while (esq >= 0) {
			refazHeap(esq, nElem);
			esq--;
		}
		while (dir > 0) {
			temp = vetor[0];
			vetor[0] = vetor[dir];
			vetor[dir] = temp;
			dir--;
			refazHeap(0, dir);
		}
	}

	private static void refazHeap(int esq, int dir) {
		int i = esq;
		int mF = 2 * i + 1;
		Promissoria raiz = vetor[i];
		boolean heap = false;

		while ((mF <= dir) && (!heap)) {
			if (mF < dir)
				if (vetor[mF].getdataVenc().compareTo(vetor[mF + 1].getdataVenc()) < 0)
					mF++;
			if (raiz.getdataVenc().compareTo(vetor[mF].getdataVenc()) < 0) {
				vetor[i] = vetor[mF];
				i = mF;
				mF = 2 * i + 1;
			} else
				heap = true;
		}
		vetor[i] = raiz;
	}

}
