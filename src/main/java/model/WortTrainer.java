package model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Das ist die Hauptklasse des Models
 *
 * @author Amadeus Abdulkarim
 * @version 18-09-2024
 */
public class WortTrainer {
    private ArrayList<WortPaar> wortListe;
    private StatistikManager statistikManager;
    private int aktuellesIndex;
    public WortTrainer(String jsonData) {
        Gson gson = new Gson();

        WortTrainer deserialized = gson.fromJson(jsonData, WortTrainer.class);

        this.wortListe = deserialized.getWortListe();
        this.statistikManager = deserialized.getStatistikManager();
        this.aktuellesIndex = deserialized.getAktuellesIndex();
    }

    /**
     * Falls keine Dateien gespeichert sind werden vorgefertigte WortPaare genommen
     */
    public WortTrainer() {
        setWortListe(new ArrayList<>());
        setStatistikManager(new StatistikManager());
        aktuellesIndex = 0;

        this.wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        this.wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        this.wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        this.wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));
    }

    /**
     * Falls keine Dateien gespeichert sind werden vorgefertigte WortPaare genommen
     * @param wortListe ist notwendig in dem Fall und kann als Lade Session verwendet werden
     */
    public WortTrainer(ArrayList<WortPaar> wortListe) {
        setWortListe(wortListe);
        setStatistikManager(new StatistikManager());

        // Add default WortPaar objects
        this.wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        this.wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        this.wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        this.wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));
    }

    /**
     * Wählt ein zufälliges Wort aus der Wortliste
     * @return model.WortPaar zufällig ausgewählt
     */
    public WortPaar nextWort() {
        Random rand = new Random();

        this.aktuellesIndex = rand.nextInt(wortListe.size());
        return getAktuellesWort();
    }

    /**
     * Überprüft ob die Antwort mit dem aktuellen Wort Paar übereinstimmt
     * @param antwort String Antwort des User
     * @return boolean
     */
    public boolean checkAntwort(String antwort) {
        boolean wahrhaftigkeit = getAktuellesWort().checkAntwort(antwort);
        if (wahrhaftigkeit) {
            statistikManager.addRichtig();
        } else {
            statistikManager.addFalsch();
        }
        nextWort();
        return wahrhaftigkeit;
    }

    public WortPaar getAktuellesWort() {
        return wortListe.get(this.aktuellesIndex);
    }

    public void setWortListe(ArrayList<WortPaar> wortListe) {
        if (wortListe != null) {
            this.wortListe = wortListe;
        } else {
            throw new IllegalArgumentException("Parameter darf nicht null sein");
        }
    }

    public void setStatistikManager(StatistikManager sm) {
        if (sm != null) {
            this.statistikManager = sm;
        } else {
            throw new IllegalArgumentException("Parameter darf nicht null sein");
        }
    }

    public void setAktuellesIndex(int aktuellesIndex) {
        this.aktuellesIndex = aktuellesIndex;
    }

    public ArrayList<WortPaar> getWortListe() {
        return wortListe;
    }

    public StatistikManager getStatistikManager() {
        return statistikManager;
    }

    public int getAktuellesIndex() {
        return aktuellesIndex;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WortTrainer that = (WortTrainer) o;
        return aktuellesIndex == that.aktuellesIndex && Objects.equals(wortListe, that.wortListe) && Objects.equals(statistikManager, that.statistikManager);
    }

}
