package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Objects;

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


    /**
     * Gibt die Gesamtzahl aller Antworten zurück.
     *
     * @return Die Anzahl der insgesamt gegebenen Antworten.
     */
    public int getInsgesamt() {
        return insgesamt;
    }

    /**
     * Setzt die Gesamtzahl aller Antworten.
     *
     * @param insgesamt Die Anzahl der insgesamt gegebenen Antworten.
     */
    public void setInsgesamt(int insgesamt) {
        this.insgesamt = insgesamt;
    }

    /**
     * Gibt die Anzahl der richtigen Antworten zurück.
     *
     * @return Die Anzahl der richtigen Antworten.
     */
    public int getAnzahlRichtig() {
        return anzahlRichtig;
    }

    /**
     * Setzt die Anzahl der richtigen Antworten.
     *
     * @param anzahlRichtig Die Anzahl der richtigen Antworten.
     */
    public void setAnzahlRichtig(int anzahlRichtig) {
        this.anzahlRichtig = anzahlRichtig;
    }

    /**
     * Gibt die Anzahl der falschen Antworten zurück.
     *
     * @return Die Anzahl der falschen Antworten.
     */
    public int getAnzahlFalsch() {
        return anzahlFalsch;
    }

    /**
     * Setzt die Anzahl der falschen Antworten.
     *
     * @param anzahlFalsch Die Anzahl der falschen Antworten.
     */
    public void setAnzahlFalsch(int anzahlFalsch) {
        this.anzahlFalsch = anzahlFalsch;
    }

    /**
     * Repräsentiert das Objekt als String Wert
     * @return
     */
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

    /**
     * Vergleicht StatistikManager für Testzwecke
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        StatistikManager that = (StatistikManager) o;
        return insgesamt == that.insgesamt && anzahlRichtig == that.anzahlRichtig && anzahlFalsch == that.anzahlFalsch;
    }

}
