package Servis;

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;

public class MealServis {
    // Referenca na MongoDB kolekciju zaduženu za skladištenje obroka
    private final MongoCollection<Document> kolekcija;

    public MealServis() {
        // Uspostavljanje konekcije sa bazom podataka i selekcija kolekcije "obroci"
        this.kolekcija = Konekcija.uzmiBazu().getCollection("obroci");
    }

    // Metoda za perzistenciju objekta Meal u MongoDB bazu podataka
    public void dodajObrok(Meal m) {
        // Konverzija objekta u Document format prije samog upisa
        kolekcija.insertOne(m.toDocument());
    }

    // Metoda za ekstrakciju svih dokumenata iz baze i njihovo mapiranje u listu objekata
    public ArrayList<Meal> uzmiSveObroke() {
        ArrayList<Meal> lista = new ArrayList<>();

        // Korištenje kursora za iteraciju kroz rezultate upita nad bazom
        try (MongoCursor<Document> cursor = kolekcija.find().iterator()) {
            while (cursor.hasNext()) {
                Document d = cursor.next();

                // Rekonstrukcija objekta Meal na osnovu podataka iz baze (Data Mapping)
                lista.add(new Meal(
                        d.getString("Dan"),
                        d.getString("Dorucak"),
                        d.getString("Rucak"),
                        d.getString("Vecera")
                ));
            }
        }
        return lista;
    }
}