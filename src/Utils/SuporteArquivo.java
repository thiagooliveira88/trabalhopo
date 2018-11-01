package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

				gravarArq.println(formatter.format(pro[i].getdataVenc()) + sp + pro[i].getNome() + sp + pro[i].getCpf()
						+ sp + pro[i].getValor() + sp + pro[i].getPaga());
			}
			gravarArq.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
