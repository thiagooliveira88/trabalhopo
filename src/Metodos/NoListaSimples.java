package Metodos;

import Objetos.Promissoria;

public class NoListaSimples {
	private Promissoria info;
	private NoListaSimples prox;
	private NoListaSimples noDataRepetida;
	
	
	public NoListaSimples(Promissoria elem) {
		this.info = elem;
		this.prox = null;
		this.noDataRepetida = null;
	}

	public Promissoria getInfo() {
		return this.info;
	}

	public void setInfo(Promissoria elem) {
		this.info = elem;
	}

	public NoListaSimples getProx() {
		return this.prox;
	}

	public void setProx(NoListaSimples novoNo) {
		this.prox = novoNo;
	}

	public NoListaSimples getNoDataRepetida() {
		return noDataRepetida;
	}

	public void setNoDataRepetida(NoListaSimples noDataRepetida) {
		this.noDataRepetida = noDataRepetida;
	}

}
