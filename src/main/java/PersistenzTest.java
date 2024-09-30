import model.WortPaar;
import model.WortTrainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PersistenzTest{

    private WortTrainer wt;
    private SpeichernLadenJson persistenceManager;

    @BeforeEach
    public void setup(){
        ArrayList<WortPaar> wortListe = new ArrayList<>();

        wortListe = new ArrayList<WortPaar>();
        wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));


        wt = new WortTrainer(wortListe);
        persistenceManager = new SpeichernLadenJson();

    }

    @Test
    @DisplayName("Test, ob eine JSON File gespeichert wird, ohne dass eine Exception geworfen wird")
    public void testSaveWTasJSONthrowsException() throws IOException{

        assertDoesNotThrow(() -> {
            persistenceManager.speichern(this.wt);
        });

    }

    @Test
    @DisplayName("Test, ob eine JSON File beim angegebenen Dateipfad vorhanden ist")
    public void testSaveWTasJSON() throws IOException{

        assertDoesNotThrow(() -> {
            String savePath = "saves/test.json";
            FileWriter fw = new FileWriter(savePath);
            new FileReader(savePath);
        });
    }

    @Test
    @DisplayName("Test, ob nach dem Speichern ein WortTrainer als JSON String zu sehen ist")
    public void testCheckFileContentAfterSave() throws IOException, IllegalAccessException{
        persistenceManager.speichern(this.wt);
        WortTrainer loadedWortTrainer = persistenceManager.laden();

        assertEquals(loadedWortTrainer.toString(), this.wt.toString());
    }




}
