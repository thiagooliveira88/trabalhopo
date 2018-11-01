<<<<<<< HEAD
package Objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promissoria {
	private String nome, cpf, valor, paga;
	private Date dataVenc;

	public String getNome() {
		return nome;
	}

	public Promissoria(String dataVenc, String nome, String cpf, String valor, String paga) {
		this.dataVenc = this.obterData(dataVenc);
		this.nome = nome;
		this.cpf = cpf;
		this.valor = valor;
		this.paga = paga;
	}

	private Date obterData(String dataVenc) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(dataVenc);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
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
=======
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
>>>>>>> 36fdd713f065e8dbc0d72e0d2a389d7ebcd5c957
