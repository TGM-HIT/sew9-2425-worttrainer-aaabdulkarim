import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Diese Klasse testet den WortTrainer
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
        wt = new WortTrainer();
        wortListe = new ArrayList<WortPaar>();
        this.wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        this.wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        this.wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        this.wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));

        sm = new StatistikManager();
    }

    @Test
    @DisplayName("Test ob der Konstruktor korrekt JSON Objekte verarbeitet")
    public void testConstructorJSON(){
        JSONObject jsonData = new JSONObject();

        jsonData.put("wortListe", wortListe);
        jsonData.put("statistikManager", sm);
        wt = new WortTrainer(jsonData);
        for (int index = 0; index < wortListe.size(); index++) {
            wt.setAktuellesIndex(index);
            assertEquals(wortListe.get(index), wt.getAktuellesWort());

        }

    }


}
