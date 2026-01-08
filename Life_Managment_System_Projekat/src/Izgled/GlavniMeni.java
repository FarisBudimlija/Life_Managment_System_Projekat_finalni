package Izgled;

import javax.swing.*;

public class GlavniMeni {

    private JPanel glavniPanel;

    // dugmad koju sam nacrtao
    private JButton btnAccount;
    private JButton btnMeal;
    private JButton btnFinance;
    private JButton btnStudy;
    private JButton btnHabit;
    private JButton btnSleep;

    public GlavniMeni() {
        // akcija za dugme account details otvaram onaj prozor za uredi profil
        btnAccount.addActionListener(e -> {
            JFrame frame = new JFrame("Uredi Profil");
            UrediProfil uredi = new UrediProfil(); // pozivam moju klasu za profil
            frame.setContentPane(uredi.getGlavniPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ugasi samo taj prozor
            frame.pack();
            frame.setLocationRelativeTo(null); // baci prozor na centar
            frame.setVisible(true);
        });

        // akcija za FinanceTracker
        btnFinance.addActionListener(e -> {
            JFrame frame = new JFrame("Finance Tracker");
            FinanceTracker finance = new FinanceTracker();
            frame.setContentPane(finance.getGlavniPanel());
            frame.pack(); // ili setSize(600, 400)
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
         //akcija za SleepTracker
        btnSleep.addActionListener(e -> {
        //  novi prozor
        JFrame frame = new JFrame("Sleep Tracker");
        SleepTracker sleep = new SleepTracker();
        frame.setContentPane(sleep.getGlavniPanel());

        // PDa se ne bi ugasio cijeli program
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Veličina
        frame.setSize(650, 450);
        // Centrira
        frame.setLocationRelativeTo(null);
        //Da se vidi
        frame.setVisible(true);
    });
        // akcija za MealPlanner
        btnMeal.addActionListener(e -> {
            // novi prozor
            JFrame frame = new JFrame("Meal Planner");
            MealPlanner planer = new MealPlanner();
            frame.setContentPane(planer.getGlavniPanel());

            // Da se ne bi ugasio cijeli program
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Veličina
            frame.setSize(650, 450);
            // Centrira
            frame.setLocationRelativeTo(null);
            // Da se vidi
            frame.setVisible(true);
        });
        // akcija za StudyPlanner
        btnStudy.addActionListener(e -> {
            // kreiramo novi prozor (okvir)
            JFrame frame = new JFrame("Study Planner");

            // kreiramo instancu tvog novog StudyPlannera
            StudyPlanner study = new StudyPlanner();

            // postavljamo panel koji smo dizajnirali u prozor
            frame.setContentPane(study.getGlavniPanel());

            // da se ugasi samo taj prozor na X
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // veličina prozora
            frame.setSize(650, 450);

            // centrira prozor na ekranu
            frame.setLocationRelativeTo(null);

            // prikazuje prozor
            frame.setVisible(true);
        });
        // Akcija za Habit Tracker
        btnHabit.addActionListener(e -> {
            // Kreiramo novi prozor koji će se zvati Habit Tracker
            JFrame frame = new JFrame("Habit Tracker");

            // Pravim instancu moje klase HabitTracker
            HabitTracker habit = new HabitTracker();

            // Uzimam onaj glavni panel što sam nacrtao i stavljam ga u prozor
            frame.setContentPane(habit.getGlavniPanel());

            // Da mi se ne ugasi cijeli projekat kad zatvorim ovaj prozor
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Postavljam istu veličinu kao i za ostale planere da bude uredno
            frame.setSize(650, 450);

            // Centriram prozor na sredinu ekrana
            frame.setLocationRelativeTo(null);

            // Prikazujem prozor korisniku
            frame.setVisible(true);
        });
}
    // metoda da main moze povuc panel i pokazat ga
    public JPanel getGlavniPanel() {
        return glavniPanel;
    }
}