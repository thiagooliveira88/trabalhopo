package Objetos;

public class Promissoria {
	private String nome, cpf, dataVenc, valor, paga;

	public String getNome() {
		return nome;
	}

	public Promissoria(String dataVenc, String nome, String cpf, String valor, String paga) {
		this.dataVenc = dataVenc;
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

	public String getdataVenc() {
		return dataVenc;
	}

	public void setdataVenc(String dataVenc) {
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
		return "\nData Vencimento = " + dataVenc 
				+ ", Nome = " + nome 
				+ ", CPF = " + cpf 
				+ ", Valor = " + valor
				+ ", Paga = " + paga;
	}
}
