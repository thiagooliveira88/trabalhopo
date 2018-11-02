package Metodos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Objetos.Promissoria;

public class UsoGeral {

	public static int pesquisaBinaria(Date dataVenc, Promissoria[] pro) {
		int meio, esq, dir;
		esq = 0;
		dir = pro.length - 1;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (dataVenc.compareTo(pro[meio].getdataVenc()) == 0)
				return meio;
			else {
				if (dataVenc.compareTo(pro[meio].getdataVenc()) < 0)
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return -1;
	}

	public static Date obterData(String dataVenc) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(dataVenc);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void escreverResultadoPesquisa(int[] vetIndices, Promissoria[] vet, String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Double totalNaoPago = 0D;
			// percorro todo o vetor
			for (int i = 0; i < vetIndices.length; i++) {

				int esq = vetIndices[i];
				// aqui se esquerda for maior que 0, será uma data válida,
				// então vou andando para esquerda verificando se as datas são iguais
				// e decremento esquerda.
				while (esq > 0 && vet[esq].getdataVenc().equals(vet[esq - 1].getdataVenc())) {
					esq--;
				}
				int dir = vetIndices[i];
				// faço a mesma coisa, só que agora para direita do vetor.
				// se as datas forem iguais vou incrementando direita
				// fazendo isso eu tenho um intervalo de indices que possuem a mesma data
				// e dará para agrupar o resultado por data.
				while (dir > 0 && dir < vet.length - 1 && vet[dir].getdataVenc().equals(vet[dir + 1].getdataVenc())) {
					dir++;
				}
				if (vetIndices[i] == -1) {
					gravarArquivo.println("NÃO HÁ PROMISSÓRIA NA DATA MENCIONADA.");
				} else {
					// percorro o intervalo de datas iguais encontradas da esquerda para direita.
					// que são pagas
					totalNaoPago = 0D;
					gravarArquivo.println("PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (vet[j].getPaga().equals("true")) {
							gravarArquivo.println(formatter.format(vet[j].getdataVenc()) + ";" + (vet[j].getValor())
									+ ";" + vet[j].getNome());
						}
					}
					// percorro o intervalo de datas iguais encontradas da esquerda para direita.
					// que não são pagas
					gravarArquivo.println("NÃO PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (vet[j].getPaga().equals("false")) {
							gravarArquivo.println(formatter.format(vet[j].getdataVenc()) + ";" + (vet[j].getValor())
									+ ";" + vet[j].getNome());
							
							totalNaoPago += Double.parseDouble(vet[j].getValor());
						}
					}
					gravarArquivo.println("TOTAL NÃO PAGA: " + totalNaoPago);
				}
			}
			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
