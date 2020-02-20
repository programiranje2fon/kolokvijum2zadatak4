package ispravka_koda;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringOperacijeTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));		
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void metoda_izbaciDuplikate() {
		List<String> lista = new LinkedList<>();
		lista.add("Pera");
		lista.add("Mika");
		lista.add("Zika");
		lista.add("Pera");
		lista.add("Pera");

		String ocekivaniIspis = 
				"[Pera, Mika, Zika]"+System.lineSeparator();
		
		StringOperacije.izbaciDuplikate(lista);
		
		assertEquals("Metoda ne izbacuje duplikate.", ocekivaniIspis, outContent.toString());
	}
}
