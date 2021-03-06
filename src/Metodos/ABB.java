package Metodos;

import Objetos.NoArv;
import Objetos.Promissoria;
import Utils.UsoGeral;

public class ABB {
	private NoArv raiz;
	private int quantNos;

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

	public boolean inserir(Promissoria elem) {
		// se o retorno for verdadeiro, quer dizer uma data igual j� foi
		// inserida na
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

	private NoArv inserir(Promissoria elem, NoArv no) {
		if (no == null) {
			NoArv novo = new NoArv(elem);
			return novo;
		} else {
			
			if (elem.getdataVenc() == no.getInfo().getdataVenc()) {

				// se for igual, vou caminhando at� o no nulo para inserir o
				// elemento.
				NoArv temp = no;
				while (temp.getNoRepetido() != null) {
					temp = temp.getNoRepetido();
				}
				// se chegou at� aqui, quer dizer que o NoRepetido esta nulo
				// insiro o elemento repetido.
				temp.setNoRepetido(new NoArv(elem));

				return no;
				
			} else if (elem.getdataVenc() < no.getInfo().getdataVenc()) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	public boolean pesquisar(int dataVenc) {
		if (pesquisar(dataVenc, raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Object pesquisarData(int dataVenc) {

		NoArv noPesquisa = pesquisar(dataVenc, raiz);

		if (noPesquisa != null)
			return noPesquisa;

		return UsoGeral.converterIntToString(dataVenc);

	}

	private NoArv pesquisar(int dataVenc, NoArv no) {
		NoArv temp = no;
		
		if (temp != null) {
			if (dataVenc < temp.getInfo().getdataVenc()) {
				temp = pesquisar(dataVenc, temp.getEsq());
			} else {
				if (dataVenc > temp.getInfo().getdataVenc()) {
					temp = pesquisar(dataVenc, temp.getDir());
				}
				
			}
		}
		return temp;
	}

	public NoArv[] CamCentral(int tamanhoVet) {
		int[] n = new int[1];
		n[0] = 0;

		NoArv[] vet = new NoArv[quantNos];

		NoArv[] vetor = FazCamCentral(raiz, vet, n);
///		raiz = null;///		quantNos = 0;

		return vetor;
	}

	private NoArv[] FazCamCentral(NoArv arv, NoArv[] vet, int[] n) {
		if (arv != null) {

			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv;
			n[0]++;
			vet = FazCamCentral(arv.getDir(), vet, n);

		}
		return vet;
	}

	public ABB ArvoreBalanceada(NoArv[] vetOrdenado) {
		ABB temp = new ABB();
		Balancear(vetOrdenado, temp, 0, vetOrdenado.length - 1);
		return temp;
	}

	private void Balancear(NoArv[] vet, ABB temp, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;

			NoArv aux = vet[meio];
			while (aux != null && aux.getInfo() != null) {
				temp.inserir(aux.getInfo());
				aux = aux.getNoRepetido();
			}

			Balancear(vet, temp, inic, meio - 1);
			Balancear(vet, temp, meio + 1, fim);
		}
	}

}
