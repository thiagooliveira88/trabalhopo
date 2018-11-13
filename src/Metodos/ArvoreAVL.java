package Metodos;

import Objetos.Promissoria;

public class ArvoreAVL {
	private static NodoAVL raiz;
	private static boolean h;

	public ArvoreAVL() {
		raiz = null;
		 h = true;
	}

	public static void insereRaiz(Promissoria elem) {
		raiz = insere(elem, raiz);
	}

	private static NodoAVL insere(Promissoria elem, NodoAVL no) {
		if (no == null) {
			NodoAVL novo = new NodoAVL(elem);
		    h = true;
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
				no.setEsq(insere(elem, no.getEsq()));
				no = balancearDir(no);
				return no;
			} else {
				// Insere à direita e verifica se precisa balancear à esquerda
				no.setDir(insere(elem, no.getDir()));
				no = balancearEsq(no);
				return no;
			}
		}
	}

	private static NodoAVL balancearDir(NodoAVL no) {
		if (h)
			switch (no.getFatorBalanceamento()) {
			case 1:
				no.setFatorBalanceamento((byte) 0);
				h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) -1);
				break;
			case -1:
				no = rotaçãoDireita(no);
			}
		return no;
	}

	private static NodoAVL balancearEsq(NodoAVL no) {
		if (h)
			switch (no.getFatorBalanceamento()) {
			case -1:
				no.setFatorBalanceamento((byte) 0);
				h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) 1);
				break;
			case 1:
				no = rotaçãoEsquerda(no);
			}
		return no;
	}

	private static NodoAVL rotaçãoDireita(NodoAVL no) {
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
		h = false;
		return no;
	}

	private static NodoAVL rotaçãoEsquerda(NodoAVL no) {
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
		h = false;
		return no;
	}

}
