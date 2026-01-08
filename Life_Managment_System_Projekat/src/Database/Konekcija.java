package Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Konekcija {

    // Ovo je adresa gdje se nalazi tvoja baza na kompjuteru
    private static final String ADRESA = "mongodb://localhost:27017";

    // Naziv baze - možeš staviti šta god želiš, npr. "MojLifeManager"
    private static final String IME_BAZE = "LifeManagerDB";

    // Ova varijabla čuva samu konekciju da je ne bismo stalno iznova otvarali
    private static MongoClient klijent = null;

    // Metoda koju ćeš zvati iz ostalih dijelova koda
    public static MongoDatabase uzmiBazu() {
        if (klijent == null) {
            try {
                // Ako klijent ne postoji, napravi novi
                klijent = MongoClients.create(ADRESA);
                System.out.println("Sistem: Uspješno smo se spojili na bazu!");
            } catch (Exception e) {
                // Ako se desi greška (npr. nisi upalio MongoDB), ispiši ovo
                System.err.println("Greška: Ne mogu se spojiti na bazu! " + e.getMessage());
            }
        }
        // Vrati nam bazu da možemo raditi s njom
        return klijent.getDatabase(IME_BAZE);
    }
}