package Izgled;

import javax.swing.*;
import Servis.KorisnikServis; // baza

public class LoginForm {
    private JPanel glavniPanel;
    private JTextField poljeIme;
    private JPasswordField poljeLozinka;
    private JButton dugmeLogin;
    private JButton dugmeReg;

    //  varijabla za servis
    private KorisnikServis servis;

    public LoginForm() {
        // inicijalizujemo servis
        servis = new KorisnikServis();

        // dugme login
        dugmeLogin.addActionListener(e -> {
            String ime = poljeIme.getText();
            String lozinka = new String(poljeLozinka.getPassword());

            if (servis.login(ime, lozinka)) {
                JOptionPane.showMessageDialog(glavniPanel, "Uspješna prijava!");
                otvoriIzbor();
            } else {
                JOptionPane.showMessageDialog(glavniPanel, "Greška: Pogrešno ime ili lozinka!");
            }
        });

        // dugme registracija
        dugmeReg.addActionListener(e -> {
            String ime = poljeIme.getText();
            String lozinka = new String(poljeLozinka.getPassword());

            if (ime.isEmpty() || lozinka.isEmpty()) {
                JOptionPane.showMessageDialog(glavniPanel, "Popuni sva polja!");
                return;
            }

            if (servis.registracija(ime, lozinka)) {
                JOptionPane.showMessageDialog(glavniPanel, "Registracija uspjela! Sad se prijavi.");
            } else {
                JOptionPane.showMessageDialog(glavniPanel, "Korisnik već postoji!");
            }
        });
    }

    // ova metoda otvori nobvi prozor
    private void otvoriIzbor() {
        // z login prozor
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(glavniPanel);
        if (frame != null) {
            frame.dispose();
        }

        // otv prozor IZBOR
        JFrame frameIzbor = new JFrame("Life Manager - Izbor");
        Izbor izborObjekat = new Izbor();

        // Povezujemo panel iz klase Izbor sa ovim frejmom
        frameIzbor.setContentPane(izborObjekat.getGlavniPanel());
        frameIzbor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameIzbor.setSize(400, 400);
        frameIzbor.setLocationRelativeTo(null); // Centriranje
        frameIzbor.setVisible(true);
    }

    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}