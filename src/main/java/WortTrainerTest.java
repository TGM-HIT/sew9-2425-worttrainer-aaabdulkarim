import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.StatistikManager;
import model.WortPaar;
import model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Diese Klasse testet den model des WortTrainer
 *
 * @author Amadeus Abdulkarim
 * @version 21-09-2024
 */
public class WortTrainerTest {

    private WortTrainer wt;
    private ArrayList<WortPaar> wortListe;
    private StatistikManager sm;
    private Gson gson = new Gson();

    @BeforeEach
    public void setup() {
        wortListe = new ArrayList<>();
        this.wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        this.wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        this.wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        this.wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));

        sm = new StatistikManager();
        wt = new WortTrainer(wortListe);
        wt.setStatistikManager(sm);
    }

    @Test
    @DisplayName("Test ob der Konstruktor vom WortTrainer korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONWortTrainer() {
        JsonObject jsonData = new JsonObject();
        jsonData.add("wortListe", gson.toJsonTree(wortListe));
        jsonData.add("statistikManager", gson.toJsonTree(sm));
        jsonData.addProperty("aktuellesIndex", 0);

        wt = gson.fromJson(jsonData, WortTrainer.class);
        for (int index = 0; index < wortListe.size(); index++) {
            wt.setAktuellesIndex(index);
            // Checkt ob die WortPaar Objekte gleicht sind.
            // Einmal Objekt von der originellen Liste vs Objekt von der gelesenen Liste
            assertTrue(wortListe.get(index).equals(wt.getAktuellesWort()));
        }
    }

    @Test
    @DisplayName("Test ob der Konstruktor vom WortPaar korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONWortPaar() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("wort", "Test");
        jsonData.addProperty("bildURL", "testURL");

        WortPaar wp = gson.fromJson(jsonData, WortPaar.class);

        assertEquals("Test", wp.getWort());
        assertEquals("testURL", wp.getBildURL());
    }

    @Test
    @DisplayName("Test ob eine Serie an nextWort Befehlen immer ein anderes zufälliges Ergebnis liefert")
    public void testNextWort() {
        ArrayList<WortPaar> randomWortPaarArr1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            randomWortPaarArr1.add(wt.nextWort());
        }

        ArrayList<WortPaar> randomWortPaarArr2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            randomWortPaarArr2.add(wt.nextWort());
        }

        assertNotEquals(randomWortPaarArr1, randomWortPaarArr2);
    }

    @Test
    @DisplayName("Test ob die checkWort Methode von der WortPaar Klasse funktioniert")
    public void testCheckWortWortPaar() {
        WortPaar wp = new WortPaar("Test", "TestURL");
        boolean ergebnisErwartetTrue = wp.checkAntwort("Test");
        assertEquals(true, ergebnisErwartetTrue);

        // Test ob Rechtschreibung ignoriert wird
        ergebnisErwartetTrue = wp.checkAntwort("test");
        assertEquals(true, ergebnisErwartetTrue);

        boolean ergebnisErwartetFalse = wp.checkAntwort("Tst");
        assertEquals(false, ergebnisErwartetFalse);
    }

    @Test
    @DisplayName("Test ob die checkWort Methode von der WortTrainer Klasse funktioniert")
    public void testCheckWortWortTrainer() {
        WortPaar wp = wt.getAktuellesWort();

        boolean ergebnisErwartetTrue = wt.checkAntwort(wp.getWort());
        assertEquals(true, ergebnisErwartetTrue);

        wp = wt.getAktuellesWort();
        // Lower Case Test
        ergebnisErwartetTrue = wt.checkAntwort(wp.getWort().toLowerCase());
        assertEquals(true, ergebnisErwartetTrue);

        wp = wt.getAktuellesWort();
        // Upper Case Test
        ergebnisErwartetTrue = wt.checkAntwort(wp.getWort().toUpperCase());
        assertEquals(true, ergebnisErwartetTrue);

        boolean ergebnisErwartetFalse = wt.checkAntwort("Tst");
        assertEquals(false, ergebnisErwartetFalse);
    }

    @Test
    @DisplayName("Test ob die Statistiken sich nach richtiger Antwort ändern")
    public void testStatistikManagerFuegHinzuRichtig() {
        int insgesamt = sm.getInsgesamt();
        int anzahlFalsch = sm.getAnzahlFalsch();
        int anzahlRichtig = sm.getAnzahlRichtig();

        wt.setAktuellesIndex(0);        // WortPaar("Gitarre", URL)
        wt.checkAntwort("Gitarre");  // Richtig wäre "Gitarre"

        assertEquals(insgesamt + 1, sm.getInsgesamt());
        assertEquals(anzahlRichtig + 1, sm.getAnzahlRichtig());
        assertEquals(anzahlFalsch, sm.getAnzahlFalsch());
    }

    @Test
    @DisplayName("Test ob die Statistiken sich nach falscher Antwort ändern")
    public void testStatistikManagerFuegHinzuFalsch() {
        int insgesamt = sm.getInsgesamt();
        int anzahlFalsch = sm.getAnzahlFalsch();
        int anzahlRichtig = sm.getAnzahlRichtig();

        wt.setAktuellesIndex(0);        // WortPaar("Gitarre", URL)
        wt.checkAntwort("Falsche Antwort"); // Richtig wäre "Gitarre"

        assertEquals(insgesamt + 1, sm.getInsgesamt());
        assertEquals(anzahlRichtig, sm.getAnzahlRichtig());
        assertEquals(anzahlFalsch + 1, sm.getAnzahlFalsch());
    }

    @Test
    @DisplayName("Test ob der Konstruktor vom StatistikManager korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONStatistikManager() {
        // Create JSON data for StatistikManager
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("insgesamt", 5);
        jsonData.addProperty("anzahlRichtig", 3);
        jsonData.addProperty("anzahlFalsch", 2);

        // Deserialize to StatistikManager
        sm = gson.fromJson(jsonData, StatistikManager.class);

        assertEquals(5, sm.getInsgesamt());
        assertEquals(3, sm.getAnzahlRichtig());
        assertEquals(2, sm.getAnzahlFalsch());
    }
}
