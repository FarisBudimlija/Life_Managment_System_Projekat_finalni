package Izgled;

import Servis.HabitServis;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HabitTracker {
    // Ovo su tvoje komponente iz UI-a
    private JPanel glavniPanel;
    private JTable habitTable;
    private JTextField navikaField;
    private JComboBox<String> comboBox1;
    private JButton dodajDugme;
    private JTextField štaŽelitePratitiTextField; // Ovo ti je naslov u UI-u vjerovatno

    private HabitServis servis;

    public HabitTracker() {
        // Inicijalizujemo servis
        servis = new HabitServis();

        // Napunimo tabelu čim se upali prozor
        osvjeziPrikaz();

        // Akcija za dugme "Dodaj"
        dodajDugme.addActionListener(e -> {
            String naziv = navikaField.getText().trim();
            String status = (String) comboBox1.getSelectedItem();

            if (!naziv.isEmpty()) {
                servis.dodajNaviku(naziv, status);
                osvjeziPrikaz();
                navikaField.setText("");
            } else {
                JOptionPane.showMessageDialog(glavniPanel, "Unesite naziv navike!");
            }
        });
    }


    public JPanel getGlavniPanel() {
        return glavniPanel;
    }

    // Logika za prikazivanje podataka u tabeli
    private void osvjeziPrikaz() {
        List<Document> zapisi = servis.uzmiSveNavike();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Navika");
        model.addColumn("Status");
        model.addColumn("Datum");

        for (Document d : zapisi) {
            model.addRow(new Object[]{
                    d.getString("navika"),
                    d.getString("status"),
                    d.get("datum") != null ? d.get("datum").toString() : ""
            });
        }
        habitTable.setModel(model);
    }
}