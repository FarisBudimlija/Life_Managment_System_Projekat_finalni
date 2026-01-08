package Servis;

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SleepServis {
    private MongoCollection<Document> kolekcija;

    public SleepServis() {
        this.kolekcija = Konekcija.uzmiBazu().getCollection("spavanje");
    }

    public String izracunajSate(String start, String end) {
        try {
            LocalTime vrijemeOd = LocalTime.parse(start);
            LocalTime vrijemeDo = LocalTime.parse(end);
            Duration razlika = Duration.between(vrijemeOd, vrijemeDo);

            if (razlika.isNegative()) {
                razlika = razlika.plusHours(24);
            }

            long h = razlika.toHours();
            long m = razlika.toMinutesPart();
            return h + "h " + m + "m";
        } catch (Exception e) {
            return "Greška";
        }
    }

    public void dodajSan(String od, String dokad, String trajanje, String kvalitet) {
        Document doc = new Document("lijeganje", od)
                .append("budjenje", dokad)
                .append("trajanje", trajanje)
                .append("kvalitet", kvalitet)
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