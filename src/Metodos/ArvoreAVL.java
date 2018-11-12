package Metodos;

import Objetos.Promissoria;

public class ArvoreAVL {
	private NodoAVL raiz;
	private boolean h;

	public ArvoreAVL() {
		this.raiz = null;
		this.h = true;
	}

	public void insereRaiz(Promissoria elem) {
		this.raiz = this.insere(elem, this.raiz);
	}

	private NodoAVL insere(Promissoria elem, NodoAVL no) {
		if (no == null) {
			NodoAVL novo = new NodoAVL(elem);
			this.h = true;
			return novo;
		} else {

			if (elem.getdataVenc().compareTo(no.getInfo().getdataVenc()) == 0) {

				// se for igual, vou caminhando até o no nulo para inserir o elemento.
				NodoAVL temp = no;
				while (temp.getNoRepetido() != null) {
					temp = temp.getNoRepetido();
				}
				// se chegou até aqui, quer dizer que o NoRepetido esta nulo
				// insiro o elemento repetido.
				temp.setNoRepetido(new NodoAVL(elem));
				return no;

			} else if (elem.getdataVenc().compareTo(no.getInfo().getdataVenc()) < 0) {
				// Insere à esquerda e verifica se precisa balancear à direita
				no.setEsq(this.insere(elem, no.getEsq()));
				no = this.balancearDir(no);
				return no;
			} else {
				// Insere à direita e verifica se precisa balancear à esquerda
				no.setDir(this.insere(elem, no.getDir()));
				no = this.balancearEsq(no);
				return no;
			}
		}
	}

	private NodoAVL balancearDir(NodoAVL no) {
		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case 1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) -1);
				break;
			case -1:
				no = this.rotaçãoDireita(no);
			}
		return no;
	}

	private NodoAVL balancearEsq(NodoAVL no) {
		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case -1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) 1);
				break;
			case 1:
				no = this.rotaçãoEsquerda(no);
			}
		return no;
	}

	private NodoAVL rotaçãoDireita(NodoAVL no) {
		NodoAVL temp1, temp2;
		temp1 = no.getEsq();
		if (temp1.getFatorBalanceamento() == -1) {
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setEsq(temp2.getDir());
			temp2.setDir(no);
			if (temp2.getFatorBalanceamento() == -1)
				no.setFatorBalanceamento((byte) 1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == 1)
				temp1.setFatorBalanceamento((byte) -1);
			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}

	private NodoAVL rotaçãoEsquerda(NodoAVL no) {
		NodoAVL temp1, temp2;
		temp1 = no.getDir();
		if (temp1.getFatorBalanceamento() == 1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setDir(temp2.getEsq());
			temp2.setEsq(no);
			if (temp2.getFatorBalanceamento() == 1)
				no.setFatorBalanceamento((byte) -1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == -1)
				temp1.setFatorBalanceamento((byte) 1);
			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}

	public Promissoria[] CamCentral(int tamanhoVet) {
		int[] n = new int[1];
		n[0] = 0;
		Promissoria[] vet = new Promissoria[tamanhoVet];

		Promissoria[] vetor = FazCamCentral(raiz, vet, n);
		return vetor;
	}

	private Promissoria[] FazCamCentral(NodoAVL arv, Promissoria[] vet, int[] n) {
		if (arv != null) {

			vet = FazCamCentral(arv.getEsq(), vet, n);

			// adiciona no vetor o nó principal
			vet[n[0]] = arv.getInfo();
			n[0]++;

			// aqui vamos pegar as datas repetidas que estão encadeadas no nó principal
			// e adicionar no vetor
			NodoAVL aux = arv.getNoRepetido();
			while (aux != null && aux.getInfo() != null) {
				vet[n[0]] = aux.getInfo();
				n[0]++;
				aux = aux.getNoRepetido();
			}
			vet = FazCamCentral(arv.getDir(), vet, n);

		}
		return vet;
	}

}
