package Izgled;

import Servis.Meal;
import Servis.MealServis;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MealPlanner {
    private JPanel glavniPanel;
    private JTable mealTable;
    private JComboBox<String> comboBox1; // ComboBox za dane u sedmici
    private JTextField dorucakField;
    private JTextField rucakField;
    private JTextField veceraField;
    private JButton dodajDugme;

    private MealServis servis; // Referenca na servisni sloj

    public MealPlanner() {
        // Inicijalizacija servisa za rad sa bazom podataka
        servis = new MealServis();

        // Učitavaje postojećih podataka iz baze u tablu pri otvaranju prozora
        osvjeziPrikaz();

        //Implementacija akcije za dodavanje novog plana obroka
        dodajDugme.addActionListener(e -> {
            String dan = (String) comboBox1.getSelectedItem();
            String dorucak = dorucakField.getText().trim();
            String rucak = rucakField.getText().trim();
            String vecera = veceraField.getText().trim();

            // Provjera unosa
            if (dorucak.isEmpty() || rucak.isEmpty() || vecera.isEmpty()) {
                JOptionPane.showMessageDialog(glavniPanel, "Molimo popunite plan za sve obroke!");
                return;
            }

            // Kreiranje objekta i perzistencija u bau podataka
            servis.dodajObrok(new Meal(dan, dorucak, rucak, vecera));

            // Ažuriranje UI komponenti
            osvjeziPrikaz();
            ocistiPolja();
        });
    }

    // Metoda za sinhronizaciju baze podataka i grafičkog prikaza u tabeli
    private void osvjeziPrikaz() {
        ArrayList<Meal> lista = servis.uzmiSveObroke();

        // Kreiranje modela tabele sa definisanim kolonama
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Dan");
        model.addColumn("Doručak");
        model.addColumn("Ručak");
        model.addColumn("Večera");

        // Mapiranje podataka iz liste u redove tabele
        for (Meal m : lista) {
            model.addRow(new Object[]{m.getDan(), m.getDorucak(), m.getRucak(), m.getVecera()});
        }
        mealTable.setModel(model);
    }

    // Pomoćna metoda za resetovanje input polja
    private void ocistiPolja() {
        dorucakField.setText("");
        rucakField.setText("");
        veceraField.setText("");
    }

    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}