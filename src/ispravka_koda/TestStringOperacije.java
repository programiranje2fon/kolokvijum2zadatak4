package ispravka_koda;

import java.util.LinkedList;
import java.util.List;

public class TestStringOperacije {

	public static void main(String[] args) {
		List<String> lista = new LinkedList<>();
		lista.add("Pera");
		lista.add("Mika");
		lista.add("Zika");
		lista.add("Pera");
		lista.add("Pera");
		
		StringOperacije.izbaciDuplikate(lista);
	}
}
