package Servis;

import Database.Konekcija;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class KorisnikServis {

    private MongoCollection<Document> korisnici;

    public KorisnikServis() {
        this.korisnici = Konekcija.uzmiBazu().getCollection("korisnici");
    }

    // Metoda za registraciju
    public boolean registracija(String ime, String lozinka) {
        Document nadjen = korisnici.find(Filters.eq("username", ime)).first();
        if (nadjen != null) {
            return false;
        }
        Document novi = new Document("username", ime)
                .append("password", lozinka);
        korisnici.insertOne(novi);
        return true;
    }

    // Login metoda
    public boolean login(String ime, String lozinka) {
        Document korisnik = korisnici.find(Filters.eq("username", ime)).first();
        if (korisnik != null) {
            String passIzBaze = korisnik.getString("password");
            if (passIzBaze.equals(lozinka)) {
                return true;
            }
        }
        return false;
    }

    // Metoda za ažuriranje
    public boolean azurirajProfil(String novoIme, String novaLozinka) {
        try {
            // Pronalazimo korisnika po imenu i mijenjamo mu lozinku
            Document updatePodaci = new Document("password", novaLozinka);

            korisnici.updateOne(
                    Filters.eq("username", novoIme),
                    new Document("$set", updatePodaci)
            );

            System.out.println("Uspješno ažurirana lozinka za korisnika: " + novoIme);
            return true;
        } catch (Exception e) {
            System.err.println("Greška prilikom ažuriranja: " + e.getMessage());
            return false;
        }
    }
}