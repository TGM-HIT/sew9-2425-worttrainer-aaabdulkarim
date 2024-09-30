package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Objects;

/**
 * Das Rückgrat der gesamten Applikation. Besteht aus einem Wort zugehörig zu der Bild URL
 *
 * @author Amadeus Abdulkarim
 * @version 18-09-2024
 */
public class WortPaar {
    private String wort;
    private String bildURL;


    public WortPaar(JsonObject jsonData){
        Gson gson = new Gson();

        WortPaar deserialized = gson.fromJson(jsonData, WortPaar.class);

        setWort(deserialized.getWort());
        setBildURL(deserialized.getBildURL());

    }

    /**
     * Benötigt Wort und Bild URL
     * @param wort String
     * @param bildURL String
     */
    public WortPaar(String wort, String bildURL) {
        setWort(wort);
        setBildURL(bildURL);
    }

    /**
     * Überprüft ob der Paramater mit dem Wort in der Klasse als Attribut übereinstimmt
     * @param antwort String Typ Antwort
     * @return True Or False als Antwort
     */
    public boolean checkAntwort(String antwort) {
        if (antwort == null || this.wort == null) {
            System.err.println("Antwort oder Wort ist null");
            return false;
        }
        return this.wort.equalsIgnoreCase(antwort);
    }

    public String getWort() {
        return wort;
    }

    public String getBildURL() {
        return bildURL;
    }

    private void setWort(String wort){
        this.wort = (wort != null) ? wort : "";
    }

    private void setBildURL(String bildURL){
        this.bildURL = (bildURL != null) ? bildURL : "";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WortPaar wortPaar = (WortPaar) o;
        return Objects.equals(wort, wortPaar.wort) && Objects.equals(bildURL, wortPaar.bildURL);
    }

}
