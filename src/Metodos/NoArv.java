package Metodos;

import Objetos.Promissoria;
public class NoArv {
	private Promissoria info; 
	private NoArv esq, dir;

	public NoArv(Promissoria elem) {
		this.info = elem;
		this.esq = null;
		this.dir = null;
	}

	public NoArv getEsq() {
		return this.esq;
	}

	public NoArv getDir() {
		return this.dir;
	}

	public Promissoria getInfo() {
		return this.info;
	}

	public void setEsq(NoArv no) {
		this.esq = no;
	}

	public void setDir(NoArv no) {
		this.dir = no;
	}

	public void setInfo(Promissoria elem) {
		this.info = elem;
	}

}
