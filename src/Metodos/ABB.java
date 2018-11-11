package Metodos;

import java.util.Date;

import Objetos.Promissoria;

public class ABB {
	private static NoArv raiz;
	private static int quantNos;

	public ABB() {
		quantNos = 0;
		raiz = null;
	}

	public boolean eVazia() {
		return (raiz == null);
	}

	public NoArv getRaiz() {
		return raiz;
	}

	public int getQuantNos() {
		return quantNos;
	}

	public static boolean inserir(Promissoria elem) {
		// se o retorno for verdadeiro, quer dizer uma data igual j� foi inserida na
		// arvore
		if (pesquisar(elem.getdataVenc())) {
			// adiciono o elemento repetido
			raiz = inserir(elem, raiz);
			return true;
		} else {
			raiz = inserir(elem, raiz);
			quantNos++;
			return true;
		}
	}

	private static NoArv inserir(Promissoria elem, NoArv no) {
		if (no == null) {
			NoArv novo = new NoArv(elem);
			return novo;
		} else {

			if (elem.getdataVenc().compareTo(no.getInfo().getdataVenc()) == 0) {

				// se for igual, vou caminhando at� o no nulo para inserir o elemento.
				NoArv temp = no;
				while (temp.getNoRepetido() != null) {
					temp = temp.getNoRepetido();
				}
				// se chegou at� aqui, quer dizer que o NoRepetido esta nulo
				// insiro o elemento repetido.
				temp.setNoRepetido(new NoArv(elem));

				return no;
			} else if (elem.getdataVenc().compareTo(no.getInfo().getdataVenc()) < 0) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	public static boolean pesquisar(Date dataVenc) {
		if (pesquisar(dataVenc, raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	private static NoArv pesquisar(Date dataVenc, NoArv no) {
		if (no != null) {
			if (dataVenc.compareTo(no.getInfo().getdataVenc()) < 0) {
				no = pesquisar(dataVenc, no.getEsq());
			} else {
				if (dataVenc.compareTo(no.getInfo().getdataVenc()) > 0) {
					no = pesquisar(dataVenc, no.getDir());
				}
			}
		}
		return no;
	}

	private NoArv Arrumar(NoArv arv, NoArv maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	public static Promissoria[] CamCentral(int tamanhoVet) {
		int[] n = new int[1];
		n[0] = 0;
		Promissoria[] vet = new Promissoria[tamanhoVet];

		Promissoria[] vetor = FazCamCentral(raiz, vet, n);

		raiz = null;
		quantNos = 0;

		return vetor;
	}

	private static Promissoria[] FazCamCentral(NoArv arv, Promissoria[] vet, int[] n) {
		if (arv != null) {

			vet = FazCamCentral(arv.getEsq(), vet, n);

			// adiciona no vetor o n� principal
			vet[n[0]] = arv.getInfo();
			n[0]++;

			// aqui vamos pegar as datas repetidas que est�o encadeadas no n� principal
			// e adicionar no vetor
			NoArv aux = arv.getNoRepetido();
			while (aux != null && aux.getInfo() != null) {
				vet[n[0]] = aux.getInfo();
				n[0]++;
				aux = aux.getNoRepetido();
			}
			vet = FazCamCentral(arv.getDir(), vet, n);

		}
		return vet;
	}

	public static ABB ArvoreBalanceada(Promissoria[] vetOrdenado) {
		ABB temp = new ABB();
		Balancear(vetOrdenado, temp, 0, vetOrdenado.length - 1);
		return temp;
	}

	private static void Balancear(Promissoria[] vet, ABB temp, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			temp.inserir(vet[meio]);
			Balancear(vet, temp, inic, meio - 1);
			Balancear(vet, temp, meio + 1, fim);
		}
	}

}
