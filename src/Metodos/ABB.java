package Metodos;

import java.text.SimpleDateFormat;
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

				// se for igual, vou caminhando até o no nulo para inserir o elemento.
				NoArv temp = no;
				while (temp.getNoRepetido() != null) {
					temp = temp.getNoRepetido();
				}
				// se chegou até aqui, quer dizer que o NoRepetido esta nulo
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

	private static NoArv pesquisarData(Date dataVenc) {

		NoArv noPesquisa = pesquisar(dataVenc, raiz);

		if (noPesquisa != null)
			return noPesquisa;

		return null;

	}

	public static String obterPesquisaString(Date data) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		NoArv noData = pesquisarData(data);
		String concatData = "";
		String separadorSplit = ",";

		if (noData != null) {
			while (noData != null && noData.getInfo() != null) {
				Promissoria info = noData.getInfo();

				concatData += formatter.format(info.getdataVenc()) + ";" + info.getNome() + ";" + info.getCpf() + ";"
						+ info.getValor() + ";" + info.getPaga() + separadorSplit;

				noData = noData.getNoRepetido();
			}
		}
		return concatData;
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

	public static NoArv[] CamCentral(int tamanhoVet) {
		int[] n = new int[1];
		n[0] = 0;

		NoArv[] vet = new NoArv[quantNos];

		NoArv[] vetor = FazCamCentral(raiz, vet, n);

		raiz = null;
		quantNos = 0;

		return vetor;
	}

	private static NoArv[] FazCamCentral(NoArv arv, NoArv[] vet, int[] n) {
		if (arv != null) {

			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv;
			n[0]++;
			vet = FazCamCentral(arv.getDir(), vet, n);

		}
		return vet;
	}

	public static ABB ArvoreBalanceada(NoArv[] vetOrdenado) {
		ABB temp = new ABB();
		Balancear(vetOrdenado, temp, 0, vetOrdenado.length - 1);
		return temp;
	}

	private static void Balancear(NoArv[] vet, ABB temp, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			temp.inserir(vet[meio].getInfo());
			Balancear(vet, temp, inic, meio - 1);
			Balancear(vet, temp, meio + 1, fim);
		}
	}

}
