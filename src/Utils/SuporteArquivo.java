package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import Objetos.Promissoria;

public class SuporteArquivo {
	public static Promissoria[] leArquivo(String arq, int tamanhoVet) {
		File f = new File(arq);
		// criar um objeto de varredura do arquivo
		Scanner sc = null;
		// criar uma lista para as promissorias
		Promissoria[] promissorias = new Promissoria[tamanhoVet];
		// l� arquivo
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
					// adicionar a promiss�ria ao vetor
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

			// percorre toda as promissorias do vetor para escrever no arquivo.
			for (int i = 0; i < pro.length; i++) {

				if (pro[i] != null) {
					gravarArq.println(UsoGeral.converterIntToString(pro[i].getdataVenc()) + sp + pro[i].getNome() + sp
							+ pro[i].getCpf() + sp + pro[i].getValor() + sp + pro[i].getPaga());
				}
			}
			gravarArq.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static int[] obterDatasParaPesquisa(String arq, int tamVet) {
		int[] datas = new int[tamVet];

		File f = new File(arq);
		Scanner sc = null;
		// l� arquivo
		try {
			sc = new Scanner(f);
			// enquanto houver linhas ...
			int i = 0;
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				// para cada campo do registro
				datas[i] = UsoGeral.converterStringToInt((linha));
				i++;

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return datas;
	}

	public static void escreverResultadoPesquisa(int[] vetIndices, int[] datas, Promissoria[] vetPromissoria,
			String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			Double totalNaoPago = 0D;
			String datasNaoEncontradas = "";
			// percorro todo o vetor
			for (int i = 0; i < vetIndices.length; i++) {

				int esq = vetIndices[i];
				// aqui se esquerda for maior que 0, ser� uma data v�lida,
				// ent�o vou andando para esquerda verificando se as datas s�o
				// iguais
				// e decremento esquerda.
				while (esq > 0 && vetPromissoria[esq].getdataVenc() == vetPromissoria[esq - 1].getdataVenc()) {
					esq--;
				}
				int dir = vetIndices[i];
				// fa�o a mesma coisa, s� que agora para direita do vetor.
				// se as datas forem iguais vou incrementando direita
				// fazendo isso eu tenho um intervalo de indices que possuem a
				// mesma data
				// e dar� para agrupar o resultado por data.
				while (dir > 0 && dir < vetPromissoria.length - 1
						&& vetPromissoria[dir].getdataVenc() == vetPromissoria[dir + 1].getdataVenc()) {
					dir++;
				}
				if (vetIndices[i] == -1) {
					// concatena as datas n�o encontradas
					datasNaoEncontradas += UsoGeral.converterIntToString(datas[i]) + " - " + "\n";
				} else {
					// percorro o intervalo de datas iguais encontradas da
					// esquerda para direita.
					// que s�o pagas
					totalNaoPago = 0D;
					gravarArquivo.println("PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (vetPromissoria[j].getPaga()) {
							gravarArquivo.println(UsoGeral.converterIntToString(vetPromissoria[j].getdataVenc()) + ";"
									+ (vetPromissoria[j].getValor()) + ";" + vetPromissoria[j].getNome());
						}
					}
					// percorro o intervalo de datas iguais encontradas da+
					// esquerda para direita.
					// que n�o s�o pagas
					gravarArquivo.println("NAO PAGAS:");
					for (int j = esq; j <= dir; j++) {
						if (!vetPromissoria[j].getPaga()) {
							gravarArquivo.println(UsoGeral.converterIntToString(vetPromissoria[j].getdataVenc()) + ";"
									+ (vetPromissoria[j].getValor()) + ";" + vetPromissoria[j].getNome());

							totalNaoPago += vetPromissoria[j].getValor();
						}
					}
					gravarArquivo.println("TOTAL NAO PAGO: " + totalNaoPago);
					gravarArquivo.println("========================================================================");
				}
			}
			// grava as datas n�o encontradas
			gravarArquivo.println("NAO HA PROMISSORIAS NAS DATAS MENCIONADAS:" + "\n" + datasNaoEncontradas);
			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void escreverResultadoPesquisa(Object[] resultadoPesquisa, String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			Double totalNaoPago = 0D;
			String datasNaoEncontradas = "";
			// percorro todo o vetor
			for (int i = 0; i < resultadoPesquisa.length; i++) {

				int esq = i;
				int dir = i;
				while (dir >= 0 && dir < resultadoPesquisa.length - 1 && ((Promissoria) resultadoPesquisa[dir])
						.getdataVenc() == ((Promissoria) resultadoPesquisa[dir + 1]).getdataVenc()) {
					dir++;
					i++;
				}

				if (dir > i) {
					i--;
				}
				Promissoria promissoria = ((Promissoria) resultadoPesquisa[i]);
				if (promissoria.getNome() == null) {
					// concatena as datas n�o encontradas
					datasNaoEncontradas += UsoGeral.converterIntToString(promissoria.getdataVenc()) + " - " + "\n";
				} else {
					// percorro o intervalo de datas iguais encontradas da
					// esquerda para direita.
					// que s�o pagas
					totalNaoPago = 0D;
					gravarArquivo.println("PAGAS:");
					for (int j = esq; j <= dir; j++) {

						Promissoria info = ((Promissoria) resultadoPesquisa[j]);

						if (info.getPaga()) {
							gravarArquivo.println(UsoGeral.converterIntToString(info.getdataVenc()) + ";"
									+ (info.getValor()) + ";" + info.getNome());
						}
					}
					// percorro o intervalo de datas iguais encontradas da+
					// esquerda para direita.
					// que n�o s�o pagas
					gravarArquivo.println("NAO PAGAS:");
					for (int j = esq; j <= dir; j++) {
						Promissoria info = ((Promissoria) resultadoPesquisa[j]);
						if (!info.getPaga()) {
							gravarArquivo.println(UsoGeral.converterIntToString(info.getdataVenc()) + ";"
									+ (info.getValor()) + ";" + info.getNome());

							totalNaoPago += info.getValor();
						}
					}
					gravarArquivo.println("TOTAL NAO PAGO: " + totalNaoPago);
					gravarArquivo.println("========================================================================");
				}

			}
			// grava as datas n�o encontradas
			gravarArquivo.println("NAO HA PROMISSORIAS NAS DATAS MENCIONADAS:" + "\n" + datasNaoEncontradas);
			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void escreverTempos(String[] tempos, String caminhoResultado) {
		try {
			FileWriter arquivo = new FileWriter(caminhoResultado);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);

			for (int i = 0; i < tempos.length; i++) {

				if (i == 0)
					gravarArquivo.println("=============METODO HEAP SORT======================================");
				else if (i == 15)
					gravarArquivo.println("=============METODO QUICK SORT=====================================");
				else if (i == 30)
					gravarArquivo.println("=============METODO ARVORE BINARIA=================================");
				else if (i == 45)
					gravarArquivo.println("=============METODO ARVORE AVL=====================================");
				else if (i == 60)
					gravarArquivo.println("=============METODO HASHING ENCADEADO==============================");

				gravarArquivo.println(tempos[i]);
			}

			gravarArquivo.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
