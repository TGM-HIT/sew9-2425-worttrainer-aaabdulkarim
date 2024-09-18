/**
 * Das Rückgrat der gesamten Applikation. Besteht aus einem Wort zugehörig zu der Bild URL
 *
 * @author Amadeus Abdulkarim
 * @version 18-09-2024
 */
public class WortPaar {
    private String wort;
    private String bildURL;

    public WortPaar(String wort, String bildURL) {
        this.wort = wort;
        this.bildURL = bildURL;
    }

    public boolean checkAntwort(String antwort) {
        return this.wort.equalsIgnoreCase(antwort);
    }

    public String getWort() {
        return wort;
    }

    public String getBildURL() {
        return bildURL;
    }
}
