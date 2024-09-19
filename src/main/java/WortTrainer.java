import java.util.ArrayList;

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

    public WortTrainer(){
        wortListe = new ArrayList<>();
        statistikManager = new StatistikManager();
        aktuellesIndex = -1;

        // TODO: Überprüfen ob es schon Daten zum Laden gibt, wenn nicht --> vorgefertigte WortTrainer verwenden
        wortListe.add(new WortPaar("Drache", "https://static.wikia.nocookie.net/drachen/images/5/5e/Bertuch_Drache_Fabelwesen_Bilderbuch_f%C3%BCr_Kinder.jpg/revision/latest?cb=20200526122105&path-prefix=de"));
        wortListe.add(new WortPaar("Gitarre", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Classical_Guitar_two_views.jpg/1024px-Classical_Guitar_two_views.jpg"));
        wortListe.add(new WortPaar("Pasta", "https://www.simply-v.de/volumes/article/articles/_768x838_crop_center-center_none/lyj5mkoECBye66gL5qULow6NgE05aDGD7yfXooqM.jpeg?v=1720169377"));
        wortListe.add(new WortPaar("Dose", "https://as1.ftcdn.net/v2/jpg/00/16/96/44/1000_F_16964494_iCMK2strv8ubvfjLB4zvgXJvR196WxO5.jpg"));
    }

    public WortTrainer(ArrayList<WortPaar> wortListe) {
        this.wortListe = wortListe;
    }
}
