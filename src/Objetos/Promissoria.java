package Objetos;

import java.util.Date;

import Metodos.UsoGeral;

public class Promissoria {
	private String nome, cpf, valor, paga;
	private Date dataVenc;

	public String getNome() {
		return nome;
	}

	public Promissoria(String dataVenc, String nome, String cpf, String valor, String paga) {
		this.dataVenc = UsoGeral.obterData(dataVenc);
		this.nome = nome;
		this.cpf = cpf;
		this.valor = valor;
		this.paga = paga;
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

	public Date getdataVenc() {
		return dataVenc;
	}

	public void setdataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPaga() {
		return paga;
	}

	public void setPaga(String paga) {
		this.paga = paga;
	}

	@Override
	public String toString() {
		return "\nData Vencimento = " + dataVenc + ", Nome = " + nome + ", CPF = " + cpf + ", Valor = " + valor
				+ ", Paga = " + paga;
	}
}
