package Izgled;

import Servis.SleepServis;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class SleepTracker {
    private JPanel glavniPanel;
    private JTable tablaSpavanje;
    private JTextField textLijeganje;
    private JTextField textBudjenje;
    private JComboBox<String> comboKvalitet;
    private JButton saveButton;

    // Povezujemo se sa servisom za bazuu
    private SleepServis servis = new SleepServis();

    public SleepTracker() {
        // Podešavanje tabele
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Datum");
        model.addColumn("Lijeganje");
        model.addColumn("Buđenje");
        model.addColumn("Ukupno sati");
        model.addColumn("Kvalitet");
        tablaSpavanje.setModel(model);

        // Učitaj stare pofatke čim se otvori prozor
        osvjeziTabelu(model);

        // Akcija na dugme Save
        saveButton.addActionListener(e -> {
            String od = textLijeganje.getText(); // npr "22:00"
            String dokad = textBudjenje.getText(); // npr "06:00"
            String kvalitet = (String) comboKvalitet.getSelectedItem();

            // Pozivamo servis da izračuna sate
            String trajanje = servis.izracunajSate(od, dokad);

            if (trajanje.equals("Greška")) {
                JOptionPane.showMessageDialog(glavniPanel, "Unesi vrijeme u formatu HH:mm (npr. 22:30)");
                return;
            }

            // Save u MongoDB
            servis.dodajSan(od, dokad, trajanje, kvalitet);

            // Osvježavamo tabelu na ekranu
            osvjeziTabelu(model);

            // Čistimo polja
            textLijeganje.setText("");
            textBudjenje.setText("");
        });
    }

    private void osvjeziTabelu(DefaultTableModel model) {
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        for (org.bson.Document d : servis.uzmiSveZapise()) {
            model.addRow(new Object[]{
                    sdf.format(d.getDate("datum")),
                    d.getString("lijeganje"),
                    d.getString("budjenje"),
                    d.getString("trajanje"),
                    d.getString("kvalitet")
            });
        }
    }

    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}