package grad;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import test.TestUtil;

public class DemografijaTest {

	@Test
	public void metoda_ucitajFestivaleIzFajla() {
		assertTrue("U interfejsu nije definisana metoda dodajGrad(Grad)", TestUtil.doesMethodExist(Demografija.class, "dodajGrad", new Class<?>[]{Grad.class}));
	}
	
	@Test
	public void metoda_objediniArhive() {
		assertTrue("U interfejsu nije definisana metoda upisiGradoveUKategorije()", TestUtil.doesMethodExist(Demografija.class, "upisiGradoveUKategorije", new Class<?>[]{}));
	}
}
