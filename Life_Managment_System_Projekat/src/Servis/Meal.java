package Servis;

import org.bson.Document;

/**
 * Model klasa koja definiše strukturu podataka za plan obroka.
 * Služi kao šablon za kreiranje objekata koji se šalju u bazu.
 */
public class Meal {
    private String dan;
    private String dorucak;
    private String rucak;
    private String vecera;

    // Konstruktor za inicijalizaciju podataka
    public Meal(String dan, String dorucak, String rucak, String vecera) {
        this.dan = dan;
        this.dorucak = dorucak;
        this.rucak = rucak;
        this.vecera = vecera;
    }

    // Pretvaranje Java objekta u format koji MongoDB razumije (BSON Document)
    public Document toDocument() {
        return new Document("Dan", dan)
                .append("Dorucak", dorucak)
                .append("Rucak", rucak)
                .append("Vecera", vecera);
    }

    // Metode za pristup podacima (Getteri)
    public String getDan() { return dan; }
    public String getDorucak() { return dorucak; }
    public String getRucak() { return rucak; }
    public String getVecera() { return vecera; }
}