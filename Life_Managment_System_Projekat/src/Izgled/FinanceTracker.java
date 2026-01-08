package Izgled;

import Servis.Transaction;
import Servis.TransactionManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FinanceTracker {
    private JPanel glavniPanel;
    private JTable transactionTable;
    private JTextField IznosField1;   // Koristimo ga za opis
    private JTextField cifraField1;   // Koristimo ga za iznos
    private JComboBox<String> typeBox1;
    private JButton dodaj;

    private TransactionManager manager;

    public FinanceTracker() {
        manager = new TransactionManager();

        // prikaz iz baze
        osvjeziPrikaz();

        dodaj.addActionListener(e -> {
            try {
                String tip = (String) typeBox1.getSelectedItem();

                // Iznos polje je IznosField1
                String tekstIznosa = IznosField1.getText().trim().replace(",", ".");
                double iznos = Double.parseDouble(tekstIznosa);

                // Artikal je cifraField1
                String opis = cifraField1.getText().trim();

                if (opis.isEmpty()) {
                    JOptionPane.showMessageDialog(glavniPanel, "Artikal ne smije biti prazan!");
                    return;
                }

                // Slanje u bazu
                manager.addTransaction(new Transaction(tip, iznos, opis));

                // Osvježavanje tabele i čišćenje polja
                osvjeziPrikaz();
                IznosField1.setText("");
                cifraField1.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(glavniPanel, "Greška! Unijeli ste '" + IznosField1.getText() + "', a ovdje mora biti broj.");
            }
        });
    }

    private void osvjeziPrikaz() {
        // Puni tabelu
        ArrayList<Transaction> lista = manager.getAllTransactions();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tip");
        model.addColumn("Iznos");
        model.addColumn("Opis");

        for (Transaction t : lista) {
            model.addRow(new Object[]{t.getType(), t.getAmount(), t.getDescription()});
        }
        transactionTable.setModel(model);
    }

    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}