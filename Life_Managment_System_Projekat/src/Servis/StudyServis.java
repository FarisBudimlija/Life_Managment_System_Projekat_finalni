package Servis; // 1. Mora biti ovaj paket

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class StudyServis { // 2. Naziv klase mora biti StudyServis
    private MongoCollection<Document> kolekcija;

    public StudyServis() {
        this.kolekcija = Konekcija.uzmiBazu().getCollection("plan_ucenja");
    }

    public void dodajPlan(String dan, String predmet) {
        Document doc = new Document("dan", dan)
                .append("predmet", predmet)
                .append("datum", new java.util.Date());
        kolekcija.insertOne(doc);
    }

    public List<Document> uzmiSveZapise() {
        List<Document> lista = new ArrayList<>();
        for (Document d : kolekcija.find()) {
            lista.add(d);
        }
        return lista;
    }
}