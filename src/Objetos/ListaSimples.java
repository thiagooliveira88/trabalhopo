package Objetos;

public class ListaSimples {
	private NoListaSimples prim;
	private NoListaSimples ult;
	private int quantNos;

	// construtor da classe
	public ListaSimples() {
		this.prim = null;
		this.ult = null;
		this.quantNos = 0;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	public NoListaSimples getPrim() {
		return this.prim;
	}

	public NoListaSimples getUlt() {
		return this.ult;
	}

	public void setQuantNos(int novoValor) {
		this.quantNos = novoValor;
	}

	public void setPrim(NoListaSimples novoNo) {
		this.prim = novoNo;
	}

	public void setUlt(NoListaSimples novoNo) {
		this.ult = novoNo;
	}

	public boolean eVazia() {
		return (this.prim == null);
	}

	public void inserirPrimeiro(Promissoria elem) {
		NoListaSimples novoNo = new NoListaSimples(elem);
		if (this.eVazia()) {
			this.ult = novoNo;
		}
		novoNo.setProx(this.prim);
		this.prim = novoNo;
		this.quantNos++;
	}

	public void inserirUltimo(Promissoria elem) {
		NoListaSimples novoNo = new NoListaSimples(elem);
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.quantNos++;
	}
}
