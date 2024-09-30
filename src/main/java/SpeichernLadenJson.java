import model.WortTrainer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

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

    public void speichern(WortTrainer wt) throws IOException, IllegalAccessException{
        JSONObject jsonData = new JSONObject();


        jsonData = convertToJSON(wt);

        try(FileWriter file = new FileWriter(speicherPfad)){
            file.write(jsonData.toJSONString());
            file.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public WortTrainer laden() throws IOException {
        JSONObject object = new JSONObject();
        try (FileReader fr = new FileReader(this.speicherPfad)) {
            JSONParser jsonParser = new JSONParser();
            object = (JSONObject) jsonParser.parse(fr);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new WortTrainer(object);
    }

    public void setSpeicherPfad(String pfad) {
        String absStandardPfad = "saves/standard.json";
        this.speicherPfad = (speicherPfad != null) ? speicherPfad : absStandardPfad;
    }

    /**
     * Diese statische Methode kann benutzt werden, um jedes Java Objekt in ein JSONObject umzuwandeln, womit das Speichern
     * erleichtert wird
     * @param obj Object da jedes Objekt unterstützt wird
     * @return JSONObject
     * @throws IllegalAccessException falls die Attribute verkapselt sind
     */
    public static JSONObject convertToJSON(Object obj) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(obj);

            if (isPrimitiveOrWrapper(value) || value instanceof String) {
                jsonObject.put(field.getName(), value);
            } else if (value != null) {
                // Falls ein Feld auch ein Objekt ist, wird diese Methode rekursiv ausgeführt
                jsonObject.put(field.getName(), convertToJSON(value));
            }

        }

        return jsonObject;
    }

    /**
     * Überprüfut ob ein Wert einen primitiven Datentyp hat oder eine Instanz einer Klasse ist
     * @param value Object
     * @return boolean
     */
    private static boolean isPrimitiveOrWrapper(Object value) {
        return (value instanceof Integer || value instanceof Long ||
                value instanceof Boolean || value instanceof Byte ||
                value instanceof Character || value instanceof Short ||
                value instanceof Double || value instanceof Float);
    }
}
