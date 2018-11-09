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
		// se o retorno for verdadeiro, quer dizer uma data igual já foi inserida na
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
				no.setNoRepetido(new NoArv(elem));
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

		return (FazCamCentral(raiz, vet, n));
	}

	private static Promissoria[] FazCamCentral(NoArv arv, Promissoria[] vet, int[] n) {
		if (arv != null) {

			vet = FazCamCentral(arv.getEsq(), vet, n);

			// adiciona no vetor o nó principal
			vet[n[0]] = arv.getInfo();
			n[0]++;

			// aqui vamos pegar as datas repetidas que estão encadeadas no nó principal
			// e adicionar no vetor
			NoArv aux = arv;
			while (aux != null && aux.getNoRepetido() != null) {
				vet[n[0]] = aux.getNoRepetido().getInfo();
				n[0]++;
				aux = aux.getNoRepetido().getNoRepetido();
			}
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}
}
