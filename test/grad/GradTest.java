package grad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import grad.exception.GradException;
import test.TestUtil;

public class GradTest {

	private Grad instance;

	@Before
	public void setUp() throws Exception {
		instance = new Grad();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_naziv() {
		assertTrue("U klasi nije definisan atribut naziv", TestUtil.doesFieldExist(Grad.class, "naziv"));
	}
	
	@Test
	public void atribut_naziv_vidljivost() {
		assertTrue("Atribut naziv nije privatan", TestUtil.hasFieldModifier(Grad.class, "naziv", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_naziv_pocetnaVrednost() {
		String naziv = (String) TestUtil.getFieldValue(instance, "naziv");
		
		assertEquals("Atribut naziv nema pocetnu vrednost \"nepoznat\".", "nepoznat", naziv);
	}
	
	@Test
	public void atribut_brojStanovnika() {
		assertTrue("U klasi nije definisan atribut brojStanovnika", TestUtil.doesFieldExist(Grad.class, "brojStanovnika"));
	}
	
	@Test
	public void atribut_brojStanovnika_vidljivost() {
		assertTrue("Atribut brojStanovnika nije privatan", TestUtil.hasFieldModifier(Grad.class, "brojStanovnika", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_brojStanovnika_pocetnaVrednost() {
		int brojStanovnika = (int) TestUtil.getFieldValue(instance, "brojStanovnika");
		
		assertEquals("Atribut brojStanovnika nema pocetnu vrednost \"0\".", 0, brojStanovnika);
	}
	
	@Test
	public void metoda_setNaziv() throws Exception {
		instance.setNaziv("Kragujevac");
		String naziv = (String) TestUtil.getFieldValue(instance, "naziv");
		assertEquals("Nakon poziva metode setNaziv(String) sa prosledjenim argumentom \"Kragujevac\", vrednost atributa naziv se nije promenila na tu vrednost", "Kragujevac", naziv);
	}
	
	@Test(expected = GradException.class)
	public void metoda_setNaziv_null() throws Exception {
		instance.setNaziv(null);
	}
	
	@Test(expected = GradException.class)
	public void metoda_setNaziv_prazanString() throws Exception {
		instance.setNaziv("");
	}
	
	@Test
	public void metoda_getNaziv() {
		String naziv = (String) TestUtil.getFieldValue(instance, "naziv");

		assertEquals("Metoda getNaziv ne vraca vrednost atributa naziv", naziv, instance.getNaziv());
	}
	
	@Test
	public void metoda_setBrojStanovnika() throws Exception {
		instance.setBrojStanovnika(200000);
		int brojStanovnika = (int) TestUtil.getFieldValue(instance, "brojStanovnika");
		assertEquals("Nakon poziva metode setBrojStanovnika(int) sa prosledjenim argumentom \"200000\", vrednost atributa brojStanovnika se nije promenila na tu vrednost", 200000, brojStanovnika);
	}
	
	@Test(expected = GradException.class)
	public void metoda_setBrojStanovnika_Nula() throws Exception {
		instance.setBrojStanovnika(0);
	}
	
	@Test
	public void metoda_getBrojStanovnika() {
		int brojStanovnika = (int) TestUtil.getFieldValue(instance, "brojStanovnika");

		assertEquals("Metoda getBrojStanovnika ne vraca vrednost atributa brojStanovnika", brojStanovnika, instance.getBrojStanovnika());
	}
	
	@Test (expected = GradException.class)
	public void metoda_equals_nijeDobraKlasa() {
		instance.equals(new Object());
	}
	
	@Test
	public void metoda_equals_isti() {
		instance.setNaziv("Kragujevac");
		
		Grad grad2 = new Grad();
		grad2.setNaziv("Kragujevac");
		
		assertEquals("Metoda equals() ne vraca vrednost true za prosledjen grad sa istim nazivom i mestom", grad2, instance);
	}
	
	@Test
	public void metoda_equals_razlicitNazivGrada() {
		instance.setNaziv("Kragujevac");
		
		Grad grad2 = new Grad();
		grad2.setNaziv("Bor");
		
		assertNotEquals("Metoda equals() ne vraca vrednost false za prosledjen grad sa razlicitim nazivom.", grad2, instance);
	}
}
