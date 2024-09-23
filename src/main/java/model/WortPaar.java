package model;

import org.json.simple.JSONObject;

/**
 * Das Rückgrat der gesamten Applikation. Besteht aus einem Wort zugehörig zu der Bild URL
 *
 * @author Amadeus Abdulkarim
 * @version 18-09-2024
 */
public class WortPaar {
    private String wort;
    private String bildURL;


    public WortPaar(JSONObject jsonData){
        setWort((String) jsonData.get("wort"));
        setBildURL((String) jsonData.get("bildURL"));

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
}
