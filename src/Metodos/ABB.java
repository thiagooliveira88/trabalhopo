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
		if (pesquisar(elem.getdataVenc())) {
			return false;
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
			if (elem.getdataVenc().compareTo(no.getInfo().getdataVenc()) < 0) {
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

	public static Promissoria[] CamCentral() {
		int[] n = new int[1];
		n[0] = 0;
		Promissoria[] vet = new Promissoria[quantNos];
		return (FazCamCentral(raiz, vet, n));
	}

	private static Promissoria[] FazCamCentral(NoArv arv, Promissoria[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}

	public Promissoria[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		Promissoria[] vet = new Promissoria[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private Promissoria[] FazCamPreFixado(NoArv arv, Promissoria[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	public Promissoria[] CamPosFixado() {
		int[] n = new int[1];
		n[0] = 0;
		Promissoria[] vet = new Promissoria[this.quantNos];
		return (FazCamPosFixado(this.raiz, vet, n));
	}

	private Promissoria[] FazCamPosFixado(NoArv arv, Promissoria[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamPosFixado(arv.getEsq(), vet, n);
			vet = FazCamPosFixado(arv.getDir(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
		}
		return vet;
	}

}
