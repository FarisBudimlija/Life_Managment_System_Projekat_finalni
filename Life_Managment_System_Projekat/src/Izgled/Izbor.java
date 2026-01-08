package Izgled;

import javax.swing.*;

public class Izbor {

    private JPanel GlavniPanel;
    private JButton dugmeProfil;
    private JButton dugmeFinansije;
    private JButton dugmeTrackeri;

    public Izbor() {
        // 1. Akcija za dugme Profil
        dugmeProfil.addActionListener(e -> {
            JFrame frameProfil = new JFrame("Uređivanje Profila");
            UrediProfil forma = new UrediProfil();

            frameProfil.setContentPane(forma.getGlavniPanel());
            frameProfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameProfil.pack();
            frameProfil.setLocationRelativeTo(null);
            frameProfil.setVisible(true);
        });

        // 2. Akcija za dugme Finansije
        dugmeFinansije.addActionListener(e -> {
            JFrame financeFrame = new JFrame("Finance Tracker");
            FinanceTracker tracker = new FinanceTracker();

            financeFrame.setContentPane(tracker.getGlavniPanel());
            financeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            financeFrame.pack();
            financeFrame.setLocationRelativeTo(null);
            financeFrame.setVisible(true);
        });

        // 3. Akcija za Trackere (Glavni Meni)
        dugmeTrackeri.addActionListener(e -> {
            JFrame frameMeni = new JFrame("Life Management System - Main Menu");
            GlavniMeni meni = new GlavniMeni();

            frameMeni.setContentPane(meni.getGlavniPanel());
            frameMeni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMeni.setSize(800, 500);
            frameMeni.setLocationRelativeTo(null);
            frameMeni.setVisible(true);
            // Zatvaramo trenutni prozor izbora
            SwingUtilities.getWindowAncestor(GlavniPanel).dispose();
        });
    }

    public JPanel getGlavniPanel() {
        return GlavniPanel;
    }
}