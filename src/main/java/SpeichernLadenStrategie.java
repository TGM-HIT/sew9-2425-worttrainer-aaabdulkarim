import model.WortTrainer;

import java.io.IOException;

/**
 * Strategy Pattern ermöglicht mehrere Persistenz Alternativen
 *
 * @author Amadeus Abdulkarim
 * @version 23-09-2024
 */
public interface SpeichernLadenStrategie{

    /**
     * Diese Methode speichert den WortTrainer.
     */
    public void speichern(WortTrainer wt) throws IOException, IllegalAccessException;


    /**
     * Diese Methode ladet den WortTrainer und gibt diesen zurück
     * @return WortTrainer
     */
    public WortTrainer laden() throws IOException;

}
