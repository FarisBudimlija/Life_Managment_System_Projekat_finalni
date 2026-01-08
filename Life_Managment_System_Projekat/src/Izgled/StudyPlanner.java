package Izgled;

import Servis.StudyServis;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudyPlanner {
    private JPanel glavniPanel;
    private JTable table1;
    private JComboBox<String> comboBox1;
    private JTextField predmetField;
    private JButton dodajDugme;

    private StudyServis servis; // Dodajemo servis

    public StudyPlanner() {
        // 1. Inicijalizacija servisa
        servis = new StudyServis();

        // 2. Učitaj podatke u tabelu čim se otvori prozor
        osvjeziPrikaz();

        // 3. Akcija za dugme "Dodaj"
        dodajDugme.addActionListener(e -> {
            String dan = (String) comboBox1.getSelectedItem();
            String predmet = predmetField.getText().trim();

            if (predmet.isEmpty()) {
                JOptionPane.showMessageDialog(glavniPanel, "Molimo unesite predmet!");
                return;
            }

            // Pozivamo metodu iz StudyServis
            servis.dodajPlan(dan, predmet);

            // Osvježi UI
            osvjeziPrikaz();
            predmetField.setText("");
        });
    }

    // Metoda za punjenje tabele
    private void osvjeziPrikaz() {
        List<Document> zapisi = servis.uzmiSveZapise();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Dan u sedmici");
        model.addColumn("Predmet");

        for (Document d : zapisi) {
            model.addRow(new Object[]{
                    d.getString("dan"),
                    d.getString("predmet")
            });
        }
        table1.setModel(model);
    }

    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}