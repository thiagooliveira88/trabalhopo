package Metodos;

import Objetos.NodoAVL;
import Objetos.Promissoria;
import Utils.UsoGeral;

public class ArvoreAVL {
	private NodoAVL raiz;
	private boolean h;

	public ArvoreAVL() {
		raiz = null;
		h = true;
	}

	public void insereRaiz(Promissoria elem) {
		raiz = insere(elem, raiz);
	}

	private NodoAVL insere(Promissoria elem, NodoAVL no) {
		if (no == null) {
			NodoAVL novo = new NodoAVL(elem);
			h = true;
			return novo;
		} else {

			// diferente da ABB, aqui eu preciso pesquisar o elemento na AVL
			// por conta do auto balanceamento da arvore
			NodoAVL noPesquisa = this.pesquisar(elem.getdataVenc(), this.raiz);
			if (noPesquisa != null) {

				// se for diferente de nulo, vou caminhando at� o no nulo para
				// inserir o
				// elemento.
				NodoAVL temp = noPesquisa;
				while (temp.getNoRepetido() != null) {
					temp = temp.getNoRepetido();
				}
				// se chegou at� aqui, quer dizer que o NoRepetido esta nulo
				// insiro o elemento repetido.
				temp.setNoRepetido(new NodoAVL(elem));

				// retorno o mesmo n�,pois nao houve alteracao
				return no;

			} else if (elem.getdataVenc() < no.getInfo().getdataVenc()) {
				// Insere � esquerda e verifica se precisa balancear � direita
				no.setEsq(insere(elem, no.getEsq()));
				no = balancearDir(no);
				return no;
			} else {
				// Insere � direita e verifica se precisa balancear � esquerda
				no.setDir(insere(elem, no.getDir()));
				no = balancearEsq(no);
				return no;
			}
		}
	}

	private NodoAVL balancearDir(NodoAVL no) {
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
				no = rota��oDireita(no);
			}
		return no;
	}

	private NodoAVL balancearEsq(NodoAVL no) {
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
				no = rota��oEsquerda(no);
			}
		return no;
	}

	private NodoAVL rota��oDireita(NodoAVL no) {
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

	private NodoAVL rota��oEsquerda(NodoAVL no) {
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

	private NodoAVL pesquisar(int dataVenc, NodoAVL no) {
		if (no != null) {
			if (dataVenc < no.getInfo().getdataVenc()) {
				no = pesquisar(dataVenc, no.getEsq());
			} else {
				if (dataVenc > no.getInfo().getdataVenc()) {
					no = pesquisar(dataVenc, no.getDir());
				}
			}
		}
		return no;
	}

	private NodoAVL pesquisarData(int dataVenc) {

		NodoAVL noPesquisa = pesquisar(dataVenc, raiz);

		if (noPesquisa != null)
			return noPesquisa;

		return null;

	}
	
	public Object pesquisar(int data) {

		NodoAVL noData = pesquisarData(data);

		if (noData != null)
			return noData;
		
		return UsoGeral.converterIntToString(data);
	}

	public String obterPesquisaString(int data) {

		NodoAVL noData = pesquisarData(data);
		String concatData = "";

		if (noData != null) {
			while (noData != null && noData.getInfo() != null) {
				Promissoria info = noData.getInfo();

				concatData += UsoGeral.converterIntToString(info.getdataVenc()) + ";" + info.getNome() + ";"
						+ info.getCpf() + ";" + info.getValor() + ";" + info.getPaga() + ",";

				noData = noData.getNoRepetido();
			}

		}

		return concatData;
	}

}
