import model.WortTrainer;

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
    public void speichern(WortTrainer wt);


    /**
     * Diese Methode ladet den WortTrainer und gibt diesen zurück
     * @return WortTrainer
     */
    public WortTrainer laden();

}
