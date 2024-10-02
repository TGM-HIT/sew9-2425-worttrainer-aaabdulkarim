package persistance;

import com.google.gson.Gson;
import model.WortTrainer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpeichernLadenJson implements SpeichernLadenStrategie {
    private String speicherPfad;

    /**
     * SpeichernLadenJson Konstruktor mit Parameter, falls ein anderer gewählt wird
     *
     * @param speicherPfad String
     */
    public SpeichernLadenJson(String speicherPfad) {
        this.setSpeicherPfad(speicherPfad);
    }

    /**
     * Konstruktor mit dem Standard Pfad
     */
    public SpeichernLadenJson() {
        String absStandardPfad = "saves/standard.json";
        this.setSpeicherPfad(absStandardPfad);
    }

    /**
     * Speicher Methode speichert den WortTrainer als JSON Datei
     * @param wt WortTrainer
     * @throws IOException wird geworfen falls die Datei nicht gefunden wird
     */
    @Override
    public void speichern(WortTrainer wt) throws IOException {
        Gson gson = new Gson();

        String jsonData = gson.toJson(wt);

        try (FileWriter file = new FileWriter(speicherPfad)) {
            file.write(jsonData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lade Methode laded den WortTrainer aus der Datei und gibt einen WortTrainer zurück
     * @return WortTrainer
     * @throws IOException
     */
    @Override
    public WortTrainer laden() throws IOException {
        WortTrainer wortTrainer = null;

        Gson gson = new Gson();

        try (FileReader fr = new FileReader(this.speicherPfad)) {
            wortTrainer = gson.fromJson(fr, WortTrainer.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return wortTrainer;
    }

    /**
     * Setzt den SpeicherPfade falls dieser geändert werden soll
     * @param pfad
     */
    public void setSpeicherPfad(String pfad) {
        String absStandardPfad = "saves/standard.json";
        this.speicherPfad = (pfad != null) ? pfad : absStandardPfad;
    }
}
