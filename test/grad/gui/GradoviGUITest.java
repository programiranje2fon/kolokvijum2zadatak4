package grad.gui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import grad.KatalogGradova;
import test.TestUtil;

public class GradoviGUITest {

	private GradoviGUI instance;

	@Before
	public void setUp() throws Exception {
		instance = new GradoviGUI();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test
	public void atribut_katalog() {
		assertTrue("U klasi nije definisan atribut katalog", TestUtil.doesFieldExist(GradoviGUI.class, "katalog"));
	}
	
	@Test
	public void atribut_katalog_vidljivost() {
		assertTrue("Atribut katalog nije privatan", TestUtil.hasFieldModifier(GradoviGUI.class, "katalog", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_katalog_pocetnaVrednost() {
		KatalogGradova katalog = (KatalogGradova) TestUtil.getFieldValue(instance, "katalog");
		
		assertNotNull("Atribut katalog nije inicijalizovan", katalog);
	}
}
