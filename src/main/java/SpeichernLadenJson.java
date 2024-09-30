import model.WortTrainer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpeichernLadenJson implements SpeichernLadenStrategie{
    private String savePath;

    @Override
    public void speichern(WortTrainer wt) throws IOException {
        JSONObject jsonData = new JSONObject();

        jsonData.put("aktuellesIndex", wt.getAktuellesWort());
        jsonData.put("statistikManager", wt.getStatistikManager());
        jsonData.put("wortListe", wt.getWortListe());

        FileWriter file = new FileWriter(savePath);
        file.write(jsonData.toJSONString());

    }

    @Override
    public WortTrainer laden() throws IOException{
        Object object = new WortTrainer();
        try (FileReader fr = new FileReader(this.savePath)){
            JSONParser jsonParser = new JSONParser();
            object = jsonParser.parse(fr);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (WortTrainer) object;

    }
}
