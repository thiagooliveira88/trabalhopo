package Metodos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Objetos.Promissoria;

public class UsoGeral {

	public static int pesquisaBinaria(int dataVenc, Promissoria[] pro) {
		int meio, esq, dir;
		esq = 0;
		dir = pro.length - 1;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (dataVenc == pro[meio].getdataVenc())
				return meio;
			else {
				if (dataVenc < pro[meio].getdataVenc())
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

	public static int converterStringToInt(String data) {

		String[] dado = data.split("/");

		return Integer.valueOf((dado[2] + dado[1] + dado[0]));

	}

	public static String converterIntToString(int data) {

		String dado = String.valueOf(data);

		String retorno = dado.substring(6, 8) + "/" + dado.substring(4, 6) + "/" + dado.substring(0, 4);
		
		return retorno;

	}

}
