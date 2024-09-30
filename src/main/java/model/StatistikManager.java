package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Der model.StatistikManager hält die Informationen zur Statistik und bietet die Funktionalität für die Änderung der Daten
 *
 * @author Amadeus Abdulkarim
 * @version 18-09-2024
 */
public class StatistikManager {
    private int insgesamt;
    private int anzahlRichtig;
    private int anzahlFalsch;

    public StatistikManager(JsonObject jsonData){
        Gson gson = new Gson();

        StatistikManager deserialized = gson.fromJson(jsonData, StatistikManager.class);

        setInsgesamt(deserialized.getInsgesamt());
        setAnzahlRichtig(deserialized.getAnzahlRichtig());
        setAnzahlFalsch(deserialized.getAnzahlFalsch());
    }

    /**
     * Anfangs wird alles auf 0 gesetzt
     */
    public StatistikManager(){
        this.insgesamt = 0;
        this.anzahlRichtig = 0;
        this.anzahlFalsch = 0;
    }

    /**
     * Fügt +1 hinzu zur Anzahl der richtigen Antworten
     */
    public void addRichtig(){
        this.insgesamt++;
        this.anzahlRichtig++;
    }

    /**
     * Fügt +1 hinzu zur Anzahl der falschen Antworten
     */
    public void addFalsch(){
        this.insgesamt++;
        this.anzahlFalsch++;
    }


    public int getInsgesamt() {
        return insgesamt;
    }

    public void setInsgesamt(int insgesamt) {
        this.insgesamt = insgesamt;
    }

    public int getAnzahlRichtig() {
        return anzahlRichtig;
    }

    public void setAnzahlRichtig(int anzahlRichtig) {
        this.anzahlRichtig = anzahlRichtig;
    }

    public int getAnzahlFalsch() {
        return anzahlFalsch;
    }

    public void setAnzahlFalsch(int anzahlFalsch) {
        this.anzahlFalsch = anzahlFalsch;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Statistik: ");
        sb.append("Insgesamt: ");
        sb.append(this.insgesamt);

        sb.append("; Richtig: ");
        sb.append(this.anzahlRichtig);

        sb.append("; Falsch: ");
        sb.append(this.anzahlFalsch);

        return sb.toString();
    }
}
