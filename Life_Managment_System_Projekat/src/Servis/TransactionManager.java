package Servis;

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;

public class TransactionManager {
    private final MongoCollection<Document> collection;

    public TransactionManager() {
        // Koristi metoodu za bazu
        this.collection = Konekcija.uzmiBazu().getCollection("transactions");
    }

    public void addTransaction(Transaction t) {
        collection.insertOne(t.toDocument());
    }

    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();
            // Mapiranje polja iz baze nazad u Transaction objekt
            String type = d.getString("Vrsta");
            double amount = d.get("Iznos") instanceof Number ? ((Number)d.get("Iznos")).doubleValue() : 0.0;
            String description = d.getString("Opis");
            list.add(new Transaction(type, amount, description));
        }
        return list;
    }
}