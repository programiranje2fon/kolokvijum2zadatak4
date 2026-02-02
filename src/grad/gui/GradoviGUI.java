package grad.gui;

import grad.Grad;
import grad.KatalogGradova;
import grad.exception.GradException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradoviGUI extends JFrame {
    private JTextField textFieldNazivGrada;
    private JTextField textFieldBrojStanovnika;
    private JButton dodajButton;
    private JButton sacuvajButton;
    private JButton obrisiButton;
    private JPanel mojPanel;

    private KatalogGradova katalog = new KatalogGradova();

    public GradoviGUI(){
        setContentPane(mojPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 150);
        setLocationRelativeTo(null);
        setTitle("Katalog gradova");
        setResizable(false);
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = textFieldNazivGrada.getText();
                    int brojStanovnika = Integer.parseInt(textFieldBrojStanovnika.getText());

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
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    katalog.upisiGradoveUKategorije();

                    JOptionPane.showMessageDialog(null, "Gradovi su sačuvani", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                } catch (GradException ex) {
                    JOptionPane.showMessageDialog(null, "Greška prilikom čuvanja gradova", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        obrisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldBrojStanovnika.setText(null);
                textFieldNazivGrada.setText(null);
            }
        });
    }

    public static void main(String[] args) {
        new GradoviGUI().setVisible(true);
    }

}
