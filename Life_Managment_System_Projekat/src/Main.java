import Izgled.LoginForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //izgled
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kreiramo okvir zjst prozor
        JFrame frame = new JFrame("Life Management System - Prijava");

        // instanca dizajna
        LoginForm prozor = new LoginForm();

        // prozor prikazuje glavni panel
        frame.setContentPane(prozor.getGlavniPanel());

        // Postavke prozora
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Prilagođava veličinu onome što si nacrtao
        frame.setSize(400, 300); // Možeš i sam odrediti veličinu
        frame.setLocationRelativeTo(null); // Centriraj na ekranu
        frame.setVisible(true); // pPrikaz prozora
    }
}