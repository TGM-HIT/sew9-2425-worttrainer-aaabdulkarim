import model.StatistikManager;
import model.WortPaar;
import model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Diese Klasse testet den model des WortTrainer
 *
 * @author Amadeus Abdulkarim
 * @version 21-09-2024
 */
public class WortTrainerTest{

    private WortTrainer wt;
    private ArrayList<WortPaar> wortListe;
    private StatistikManager sm;

    @BeforeEach
    public void setup(){
        wortListe = new ArrayList<WortPaar>();
        this.wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        this.wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        this.wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        this.wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));

        sm = new StatistikManager();
        wt = new WortTrainer(wortListe);
        wt.setStatistikManager(sm);
    }

    @Test
    @DisplayName("Test ob der Konstruktor vom model.WortTrainer korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONWortTrainer(){
        JSONObject jsonData = new JSONObject();

        jsonData.put("wortListe", wortListe);
        jsonData.put("statistikManager", sm);
        wt = new WortTrainer(jsonData);
        for(int index = 0; index < wortListe.size(); index++){
            wt.setAktuellesIndex(index);
            assertEquals(wortListe.get(index), wt.getAktuellesWort());
        }

    }

    @Test
    @DisplayName("Test ob der Konstruktor vom model.WortPaar korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONWortPaar(){
        JSONObject jsonData = new JSONObject();
        jsonData.put("wort", "Test");
        jsonData.put("bildURL", "testURL");

        WortPaar wp = new WortPaar(
                (String) jsonData.get("wort"),
                (String) jsonData.get("bildURL")
        );

        assertEquals(wp.getWort(), "Test");
        assertEquals(wp.getBildURL(), "testURL");

    }

    @Test
    @DisplayName("Test ob eine Serie an nextWort Befehlen immer ein anderes zufälliges Ergebnis liefert")
    /**
     * In einem sehr unwahrscheinlichen Fall wird dieser Test fehlschlagen
     */
    public void testNextWort(){
        ArrayList<WortPaar> randomWortPaarArr1 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            randomWortPaarArr1.add(wt.nextWort());
        }

        ArrayList<WortPaar> randomWortPaarArr2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            randomWortPaarArr2.add(wt.nextWort());
        }

        assertNotEquals(randomWortPaarArr1, randomWortPaarArr2);
    }

    @Test
    @DisplayName("Test ob die checkWort Methode von der model.WortPaar Klasse funktioniert")
    public void testCheckWortWortPaar(){
        WortPaar wp = new WortPaar("Test", "TestURL");
        boolean ergebnisErwartetTrue = wp.checkAntwort("Test");
        assertEquals(true, ergebnisErwartetTrue);

        // Test ob Rechtschreibung ingoriert wird
        ergebnisErwartetTrue = wp.checkAntwort("test");
        assertEquals(true, ergebnisErwartetTrue);

        boolean ergebnisErwartetFalse = wp.checkAntwort("Tst");
        assertEquals(false, ergebnisErwartetFalse);
    }


    @Test
    @DisplayName("Test ob die checkWort Methode von der model.WortTrainer Klasse funktioniert")
    public void testCheckWortWortTrainer(){
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
    public void testStatistikManagerFuegHinzuRichtig(){
        int insgesamt = sm.getInsgesamt();
        int anzahlFalsch = sm.getAnzahlFalsch();
        int anzahlRichtig = sm.getAnzahlRichtig();

        wt.setAktuellesIndex(0);        // model.WortPaar("Gitarre", URL)
        wt.checkAntwort("Gitarre");  // Richtig wäre "Gitarre"

        assertEquals(insgesamt + 1, sm.getInsgesamt());
        assertEquals(anzahlRichtig + 1, sm.getAnzahlRichtig());
        assertEquals(anzahlFalsch, sm.getAnzahlFalsch());
    }

    @Test
    @DisplayName("Test ob die Statistiken sich nach falscher Antwort aendern")
    public void testStatistikManagerFuegHinzuFalsch(){
        int insgesamt = sm.getInsgesamt();
        int anzahlFalsch = sm.getAnzahlFalsch();
        int anzahlRichtig = sm.getAnzahlRichtig();

        wt.setAktuellesIndex(0);        // model.WortPaar("Gitarre", URL)
        wt.checkAntwort("Falsche Antwort"); // Richtig wäre "Gitarre"

        assertEquals(insgesamt + 1, sm.getInsgesamt());
        assertEquals(anzahlRichtig, sm.getAnzahlRichtig());
        assertEquals(anzahlFalsch + 1, sm.getAnzahlFalsch());
    }

    @Test
    @DisplayName("Test ob der Konstruktor vom model.StatistikManager korrekt JSON Objekte verarbeitet")
    public void testConstructorJSONStatistikManager(){
        JSONObject jsonData = new JSONObject();
        jsonData.put("insgesamt", 5);
        jsonData.put("anzahlRichtig", 3);
        jsonData.put("anzahlFalsch", 2);

        sm = new StatistikManager(jsonData);

        int insgesamt = sm.getInsgesamt();
        int anzahlFalsch = sm.getAnzahlFalsch();
        int anzahlRichtig = sm.getAnzahlRichtig();

        assertEquals(5, sm.getInsgesamt());
        assertEquals(3, sm.getAnzahlRichtig());
        assertEquals(2, sm.getAnzahlFalsch());
    }

}
