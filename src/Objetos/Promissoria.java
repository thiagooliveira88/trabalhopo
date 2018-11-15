package Objetos;

import Utils.UsoGeral;

public class Promissoria {
	private String nome, cpf, valor;
	private int dataVenc;
	private boolean paga;

	public String getNome() {
		return nome;
	}

	public Promissoria(String dataVenc, String nome, String cpf, String valor, String paga) {
		this.dataVenc = UsoGeral.converterStringToInt(dataVenc);
		this.nome = nome;
		this.cpf = cpf;
		this.valor = valor;
		this.paga = Boolean.parseBoolean(paga);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getdataVenc() {
		return dataVenc;
	}

	public void setdataVenc(int dataVenc) {
		this.dataVenc = dataVenc;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean getPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	@Override
	public String toString() {
		return "\nData Vencimento = " + dataVenc + ", Nome = " + nome + ", CPF = " + cpf + ", Valor = " + valor
				+ ", Paga = " + paga;
	}
}
