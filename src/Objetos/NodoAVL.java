package Objetos;

public class NodoAVL {
	private Promissoria info;
	private NodoAVL esq, dir, noRepetido;
	private byte fatorBalanceamento;

	public NodoAVL(Promissoria promissoria) {
		this.info = promissoria;
		this.fatorBalanceamento = 0;
		this.esq = null;
		this.dir = null;
		this.noRepetido = null;
	}

	public NodoAVL getDir() {
		return this.dir;
	}

	public void setDir(NodoAVL dir) {
		this.dir = dir;
	}

	public NodoAVL getEsq() {
		return this.esq;
	}

	public void setEsq(NodoAVL esq) {
		this.esq = esq;
	}

	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;

	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}

	public Promissoria getInfo() {
		return this.info;
	}

	public NodoAVL getNoRepetido() {
		return noRepetido;
	}

	public void setNoRepetido(NodoAVL noRepetido) {
		this.noRepetido = noRepetido;
	}
}
