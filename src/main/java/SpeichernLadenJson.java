import com.google.gson.Gson;
import model.WortTrainer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpeichernLadenJson implements SpeichernLadenStrategie {
    private String speicherPfad;

    public SpeichernLadenJson(String speicherPfad) {
        this.setSpeicherPfad(speicherPfad);
    }

    public SpeichernLadenJson() {
        String absStandardPfad = "saves/standard.json";
        this.setSpeicherPfad(absStandardPfad);
    }

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

    public void setSpeicherPfad(String pfad) {
        String absStandardPfad = "saves/standard.json";
        this.speicherPfad = (pfad != null) ? pfad : absStandardPfad;
    }
}
