package grad;
import java.io.Serializable;

import grad.exception.GradException;

public class Grad implements Serializable {

	private static final long serialVersionUID = 1L;

	private String naziv = "nepoznat";
	private int brojStanovnika = 0;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		if (naziv == null || naziv.length() < 2) {
			throw new GradException("Naziv ne moze biti null ili string manji od dva karaktera");
		}
		this.naziv = naziv;
	}

	public int getBrojStanovnika() {
		return brojStanovnika;
	}

	public void setBrojStanovnika(int brojStanovnika) {
		if (brojStanovnika <= 0) {
			throw new GradException("Broj stanovnika mora biti veci od nule.");
		}
		this.brojStanovnika = brojStanovnika;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Grad)) {
			throw new GradException("Ovo nije objekat klase Grad.");
		}
		
		Grad g = (Grad) obj;
		
		return this.getNaziv().equals(g.getNaziv());
	}
}
