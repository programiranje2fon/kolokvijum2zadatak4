package grad.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import grad.Grad;
import grad.KatalogGradova;
import grad.exception.GradException;

public class GradoviGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField jtfNazivGrada;
	private JTextField jtfBrojStanovnika;

	private KatalogGradova katalog = new KatalogGradova();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradoviGUI frame = new GradoviGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GradoviGUI() {
		setResizable(false);
		setTitle("Katalog gradova");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNazivGrada = new JLabel("Naziv grada");
		lblNazivGrada.setBounds(29, 19, 116, 16);
		contentPane.add(lblNazivGrada);

		JLabel lblBrojStanovnika = new JLabel("Broj stanovnika");
		lblBrojStanovnika.setBounds(230, 19, 145, 16);
		contentPane.add(lblBrojStanovnika);

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String naziv = jtfNazivGrada.getText();
					int brojStanovnika = Integer.parseInt(jtfBrojStanovnika.getText());

					Grad noviGrad = new Grad();
					noviGrad.setNaziv(naziv);
					noviGrad.setBrojStanovnika(brojStanovnika);
					
					katalog.dodajGrad(noviGrad);

					JOptionPane.showMessageDialog(null, "Grad je uspešno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
				} catch (GradException ex) {
					JOptionPane.showMessageDialog(null, "Greška prilikom dodavanja grada", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDodaj.setBounds(28, 99, 117, 29);
		contentPane.add(btnDodaj);

		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					katalog.upisiGradoveUKategorije();
					
					JOptionPane.showMessageDialog(null, "Gradovi su sačuvani", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
				} catch (GradException ex) {
					JOptionPane.showMessageDialog(null, "Greška prilikom čuvanja gradova", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSacuvaj.setBounds(157, 99, 117, 29);
		contentPane.add(btnSacuvaj);

		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfNazivGrada.setText(null);
				jtfBrojStanovnika.setText(null);
			}
		});
		btnObrisi.setBounds(286, 99, 117, 29);
		contentPane.add(btnObrisi);

		jtfNazivGrada = new JTextField();
		jtfNazivGrada.setBounds(28, 47, 134, 28);
		contentPane.add(jtfNazivGrada);
		jtfNazivGrada.setColumns(10);

		jtfBrojStanovnika = new JTextField();
		jtfBrojStanovnika.setBounds(230, 47, 134, 28);
		contentPane.add(jtfBrojStanovnika);
		jtfBrojStanovnika.setColumns(10);
	}
}
