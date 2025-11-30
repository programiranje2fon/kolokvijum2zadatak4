package grad;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import grad.exception.GradException;

public class KatalogGradova implements Demografija {
	
	private List<Grad> gradovi = new LinkedList<>();
	
	@Override
	public void dodajGrad(Grad grad) {
		if (!gradovi.contains(grad)) {
			// ova promenljiva nam sluzi kao indikator da znamo da li smo uneli grad u listu ili ne
			boolean unesen = false;
			
			for (int i = 0; i < gradovi.size(); i++) {
				if (gradovi.get(i).getBrojStanovnika() < grad.getBrojStanovnika()) {
					gradovi.add(i, grad);
					unesen = true;
					break;
				}
			}
			
			// Kada promenljiva 'unesen' ima vrednost false, to oznacava situaciju kada u listi 
			// svi gradovi imaju veci broj stanovnika od novog grada i stoga uslov u petlji nijednom 
			// nije bio ispunjen. U tom slucaju, novi grad unosimo na kraj liste.
			if (!unesen) {
				gradovi.add(gradovi.size(), grad);
			}
		} else {
			throw new GradException("Grad vec postoji u katalogu");
		}
	}

	@Override
	public void upisiGradoveUKategorije() {
		try (ObjectOutputStream maliGradoviOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("mali_gradovi.out")));
				ObjectOutputStream srednjiGradoviOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("srednji_gradovi.out")));
				ObjectOutputStream velikiGradoviOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("veliki_gradovi.out")));){

			for (int i = 0; i < gradovi.size(); i++) {
				if (gradovi.get(i).getBrojStanovnika() < 100000) {
					maliGradoviOut.writeObject(gradovi.get(i));
				} else if (gradovi.get(i).getBrojStanovnika() <= 1000000) {
					srednjiGradoviOut.writeObject(gradovi.get(i));
				} else {
					velikiGradoviOut.writeObject(gradovi.get(i));
				}
			}
		} catch (Exception ex) {
			throw new GradException("Greska prilikom upisivanja u fajlove.");
		}
	}
}
