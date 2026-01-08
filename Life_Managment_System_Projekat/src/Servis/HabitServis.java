package Servis;

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class HabitServis {
    private MongoCollection<Document> kolekcija;

    public HabitServis() {
        // Povezivanje na kolekciju navike
        this.kolekcija = Konekcija.uzmiBazu().getCollection("navike");
    }

    public void dodajNaviku(String naziv, String status) {
        Document doc = new Document("navika", naziv)
                .append("status", status)
                .append("datum", new java.util.Date());
        kolekcija.insertOne(doc);
    }

    public List<Document> uzmiSveNavike() {
        List<Document> lista = new ArrayList<>();
        for (Document d : kolekcija.find()) {
            lista.add(d);
        }
        return lista;
    }
}