package Izgled;

import javax.swing.*;
import Servis.KorisnikServis; // provjera foldera servius

public class UrediProfil {
    private JPanel glavniPanel;
    private JTextField poljeNovoIme;
    private JPasswordField poljeNovaSifra;
    private JButton dugmeSpasi;

    private KorisnikServis servis;

    public UrediProfil() {
        servis = new KorisnikServis();

        // save dugme
        dugmeSpasi.addActionListener(e -> {
            String ime = poljeNovoIme.getText();
            String lozinka = new String(poljeNovaSifra.getPassword());

            if (ime.isEmpty() || lozinka.isEmpty()) {
                JOptionPane.showMessageDialog(glavniPanel, "Polja ne smiju biti prazna!");
                return;
            }

            // Ovdje pozivamo bazu
            if (servis.azurirajProfil(ime, lozinka)) {
                JOptionPane.showMessageDialog(glavniPanel, "Profil uspješno ažuriran!");

                // Zatvaramo samo ovaj prozor
                JFrame prozor = (JFrame) SwingUtilities.getWindowAncestor(glavniPanel);
                prozor.dispose();
            } else {
                JOptionPane.showMessageDialog(glavniPanel, "Greška pri čuvanju podataka!");
            }
        });
    }

    // Ovo je obavezno da bi drugi prozori mogli dohvatiti ovaj dizajn
    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}