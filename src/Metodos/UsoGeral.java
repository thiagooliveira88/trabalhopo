package Metodos;

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
}
