package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import Metodos.UsoGeral;
import Objetos.Promissoria;

public class SuporteArquivo {
	public static Promissoria[] leArquivo(String arq, int tamanhoVet) {
		File f = new File(arq);
		// criar um objeto de varredura do arquivo
		Scanner sc = null;
		// criar uma lista para as promissorias
		Promissoria[] promissorias = new Promissoria[tamanhoVet];
		// lê arquivo
		try {
			sc = new Scanner(f);
			// enquanto houver linhas ...
			int i = 0;
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				// dividir a linha pelo separador ;
				StringTokenizer st = new StringTokenizer(linha, ";");
				// para cada campo do registro
				while (st.hasMoreElements()) {
					// extrair a promissoria
					Promissoria p = new Promissoria(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
							st.nextToken());
					// adicionar a promissória ao vetor
					promissorias[i] = p;
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return promissorias;
	}

	public static void escreverArquivo(Promissoria[] pro, String caminhoArquivo) {
		try {
			FileWriter arq = new FileWriter(caminhoArquivo);
			PrintWriter gravarArq = new PrintWriter(arq);

			// separador dos campos
			String sp = ";";

			// retornar a data para string
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			// percorre toda as promissorias do vetor para escrever no arquivo.
			for (int i = 0; i < pro.length; i++) {

				if (pro[i] != null) {
					gravarArq.println(formatter.format(pro[i].getdataVenc()) + sp + pro[i].getNome() + sp
							+ pro[i].getCpf() + sp + pro[i].getValor() + sp + pro[i].getPaga());
				}
			}
			gravarArq.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Date[] obterDatasParaPesquisa(String arq, int tamVet) {
		Date[] datas = new Date[tamVet];

		File f = new File(arq);
		Scanner sc = null;
		// lê arquivo
		try {
			sc = new Scanner(f);
			// enquanto houver linhas ...
			int i = 0;
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				// para cada campo do registro
				datas[i] = UsoGeral.obterData(linha);
				i++;

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return datas;
	}

	public static void escreverResultadoPesquisa(int[] vetIndices, Date[] datas, Promissoria[] vetPromissoria,
			String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Double totalNaoPago = 0D;
			String datasNaoEncontradas = "";
			// percorro todo o vetor
			for (int i = 0; i < vetIndices.length; i++) {

				int esq = vetIndices[i];
				// aqui se esquerda for maior que 0, será uma data válida,
				// então vou andando para esquerda verificando se as datas são iguais
				// e decremento esquerda.
				while (esq > 0 && vetPromissoria[esq].getdataVenc().equals(vetPromissoria[esq - 1].getdataVenc())) {
					esq--;
				}
				int dir = vetIndices[i];
				// faço a mesma coisa, só que agora para direita do vetor.
				// se as datas forem iguais vou incrementando direita
				// fazendo isso eu tenho um intervalo de indices que possuem a mesma data
				// e dará para agrupar o resultado por data.
				while (dir > 0 && dir < vetPromissoria.length - 1
						&& vetPromissoria[dir].getdataVenc().equals(vetPromissoria[dir + 1].getdataVenc())) {
					dir++;
				}
				if (vetIndices[i] == -1) {
					// concatena as datas não encontradas
					datasNaoEncontradas += formatter.format(datas[i]) + "\n";
				} else {
					// percorro o intervalo de datas iguais encontradas da esquerda para direita.
					// que são pagas
					totalNaoPago = 0D;
					gravarArquivo.println("PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (Boolean.parseBoolean(vetPromissoria[j].getPaga())) {
							gravarArquivo.println(formatter.format(vetPromissoria[j].getdataVenc()) + ";"
									+ (vetPromissoria[j].getValor()) + ";" + vetPromissoria[j].getNome());
						}
					}
					// percorro o intervalo de datas iguais encontradas da esquerda para direita.
					// que não são pagas
					gravarArquivo.println("NÃO PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (!Boolean.parseBoolean(vetPromissoria[j].getPaga())) {
							gravarArquivo.println(formatter.format(vetPromissoria[j].getdataVenc()) + ";"
									+ (vetPromissoria[j].getValor()) + ";" + vetPromissoria[j].getNome());

							totalNaoPago += Double.parseDouble(vetPromissoria[j].getValor());
						}
					}
					gravarArquivo.println("TOTAL NÃO PAGA: " + totalNaoPago);
					gravarArquivo.println("========================================================================");
				}
			}
			// grava as datas não encontradas
			gravarArquivo.println("NÃO HÁ PROMISSÓRIAS NAS DATAS MENCIONADAS:" + "\n" + datasNaoEncontradas);
			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
