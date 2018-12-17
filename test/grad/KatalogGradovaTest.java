package grad;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import grad.exception.GradException;
import test.TestUtil;

public class KatalogGradovaTest {

	private KatalogGradova instance;

	@Before
	public void setUp() throws Exception {
		instance = new KatalogGradova();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test
	public void atribut_gradovi() {
		assertTrue("U klasi nije definisan atribut gradovi", TestUtil.doesFieldExist(KatalogGradova.class, "gradovi"));
	}

	@Test
	public void atribut_gradovi_vidljivost() {
		assertTrue("Atribut gradovi nije privatan", TestUtil.hasFieldModifier(KatalogGradova.class, "gradovi", Modifier.PRIVATE));
	}

	@Test
	public void metoda_dodajGrad() {
		Grad grad1 = kreirajGrad("Kragujevac", 180000);
		Grad grad2 = kreirajGrad("Beograd", 1700000);
		Grad grad3 = kreirajGrad("Novi Sad", 350000);
		Grad grad4 = kreirajGrad("Kraljevo", 125000);

		@SuppressWarnings("unchecked")
		List<Grad> gradovi = (List<Grad>) TestUtil.getFieldValue(instance, "gradovi");

		instance.dodajGrad(grad1);
		assertTrue("Nakon unosenja grada Kragujevac (180000), Kragujevac se ne nalazi se na prvom mestu u listi",
				gradovi.get(0) == grad1);

		instance.dodajGrad(grad2);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), Beograd se ne nalazi se na prvom mestu u listi gradovi.",
				gradovi.get(0) == grad2);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), Kragujevac se ne nalazi se na drugom mestu u listi gradovi.",
				gradovi.get(1) == grad1);

		instance.dodajGrad(grad3);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), Beograd se ne nalazi se na prvom mestu u listi gradovi.",
				gradovi.get(0) == grad2);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), Novi Sad se ne nalazi se na drugom mestu u listi gradovi.",
				gradovi.get(1) == grad3);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), Kragujevac se ne nalazi se na trecem mestu u listi gradovi.",
				gradovi.get(2) == grad1);

		instance.dodajGrad(grad4);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), pa Kraljevo (125000), Beograd se ne nalazi se na prvom mestu u listi gradovi.",
				gradovi.get(0) == grad2);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), pa Kraljevo (125000), Novi Sad se ne nalazi se na drugom mestu u listi gradovi.",
				gradovi.get(1) == grad3);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), pa Kraljevo (125000), Kragujevac se ne nalazi se na trecem mestu u listi gradovi.",
				gradovi.get(2) == grad1);
		assertTrue(
				"Nakon unosenja grada Kragujevac (180000), pa Beograd (1700000), pa Novi Sad (350000), pa Kraljevo (125000), Kraljevo se ne nalazi se na cetvrtom mestu u listi gradovi.",
				gradovi.get(3) == grad4);
	}

	@Test(expected = GradException.class)
	public void metoda_dodajGrad_istiGrad() {
		Grad grad1 = kreirajGrad("Kragujevac", 180000);

		instance.dodajGrad(grad1);
		instance.dodajGrad(grad1);
	}

	@Test
	public void metoda_upisiGradoveUKategorije() throws Exception {
		Grad grad1 = kreirajGrad("grad1", 1);
		Grad grad2 = kreirajGrad("grad2", 99999);
		Grad grad3 = kreirajGrad("grad3", 100000);
		Grad grad4 = kreirajGrad("grad4", 350000);
		Grad grad5 = kreirajGrad("grad5", 1000000);
		Grad grad6 = kreirajGrad("grad6", 1000001);
		Grad grad7 = kreirajGrad("grad7", 5000000);

		instance.dodajGrad(grad1);
		instance.dodajGrad(grad2);
		instance.dodajGrad(grad3);
		instance.dodajGrad(grad4);
		instance.dodajGrad(grad5);
		instance.dodajGrad(grad6);
		instance.dodajGrad(grad7);

		instance.upisiGradoveUKategorije();

		List<Grad> maliGradovi = ucitajGradoveIzFajla("mali_gradovi.out");
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu mali_gradovi.out se ne nalazi grad sa brojem stanovnika 1", maliGradovi.contains(grad1));
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu mali_gradovi.out se ne nalazi grad sa brojem stanovnika 99999", maliGradovi.contains(grad2));
	
		List<Grad> srednjiGradovi = ucitajGradoveIzFajla("srednji_gradovi.out");
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu srednji_gradovi.out se ne nalazi grad sa brojem stanovnika 100000", srednjiGradovi.contains(grad3));
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu srednji_gradovi.out se ne nalazi grad sa brojem stanovnika 350000", srednjiGradovi.contains(grad4));
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu srednji_gradovi.out se ne nalazi grad sa brojem stanovnika 1000000", srednjiGradovi.contains(grad5));
		
		List<Grad> velikiGradovi = ucitajGradoveIzFajla("veliki_gradovi.out");
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu srednji_gradovi.out se ne nalazi grad sa brojem stanovnika 1000001", velikiGradovi.contains(grad6));
		assertTrue("Kada se u katalog dodaju gradovi sa sledecim brojem stanovnika: 1, 99999, 100000, 350000, 1000000, 1000001 i 5000000, u fajlu srednji_gradovi.out se ne nalazi grad sa brojem stanovnika 5000000", velikiGradovi.contains(grad7));
	
		// brisemo kreirani fajl
		new File("mali_gradovi.out").delete();
		new File("srednji_gradovi.out").delete();
		new File("veliki_gradovi.out").delete();
	}

	private Grad kreirajGrad(String naziv, int brojStanovnika) {
		Grad grad = new Grad();
		grad.setNaziv(naziv);
		grad.setBrojStanovnika(brojStanovnika);
		return grad;
	}

	private List<Grad> ucitajGradoveIzFajla(String imeFajla) {
		List<Grad> gradovi = new LinkedList<>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(imeFajla))) {
			while (true) {
				Grad grad = (Grad) in.readObject();
				gradovi.add(grad);
			}
		} catch (Exception e) {
		}
		return gradovi;
	}
}
