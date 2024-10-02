package control;

import model.WortPaar;
import model.WortTrainer;
import persistance.SpeichernLadenJson;
import persistance.SpeichernLadenStrategie;
import view.ViewWortTrainer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Die ControlWortTrainer Klasse ist der Controller der MVC Struktur
 * Diese Komponente hält die View und das Model und bietet die Logik der Applikation
 *
 * @author Amadeus Abdulkarim
 * @version 02-10-2024
 */
public class ControlWortTrainer implements ActionListener, DocumentListener {

    private WortTrainer model;
    private ViewWortTrainer view;

    private SpeichernLadenStrategie speicherStrategie;

    /**
     * Konstruktor der Controller Klasse ohne Parameter
     */
    public ControlWortTrainer(){
        this.model = new WortTrainer();
        this.view = new ViewWortTrainer(this);
        this.speicherStrategie = new SpeichernLadenJson();
        setBildUrl();

    }

    /**
     * Konstruktor der Controller Klasse mit Parameter
     *
     * @param model vorhandenes Model
     * @param view vorhandene View
     * @param speicherStrategie vorhandene Speicherstrategie, welche vom Interface SpeicherStrategie implementiert
     */
    public ControlWortTrainer(WortTrainer model, ViewWortTrainer view, SpeichernLadenStrategie speicherStrategie){
        this.model = model;
        this.view = view;
        this.speicherStrategie = speicherStrategie;
        setBildUrl();

    }

    /**
     *
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String ac = e.getActionCommand();

        // Antwort Auf Save Button
        if (ac.equalsIgnoreCase("save")) {
            try {
                speicherStrategie.speichern(model);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        // Antwort auf Load Button
        else if (ac.equalsIgnoreCase("load")) {
            try {
                WortTrainer geladenesModel = speicherStrategie.laden();
                this.model = geladenesModel;
                this.view.setStatistik(this.model.getStatistikManager());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Antwort auf Nextes Wort
        else if (ac.equalsIgnoreCase("next")) {
            String userInput = view.getUserInput();
            this.model.checkAntwort(userInput);
            this.view.setStatistik(model.getStatistikManager());

            setBildUrl();

        }


    }

    /**
     * Diese Methode setzt vom Model die BildURL in das JLabel von der View
     */
    public void setBildUrl() {
        WortPaar aktuellesWortPaar = this.model.getAktuellesWort();

        try {
            this.view.setBildUrl(aktuellesWortPaar.getBildURL());
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // DocumentListener methods to monitor JTextField input
    @Override
    public void insertUpdate(DocumentEvent e) {
        checkUserInput();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkUserInput();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        checkUserInput();
    }

    /**
     * Checkt den User INput
     */
    private void checkUserInput() {
        String userInput = view.getUserInput();
        view.setButtonEnabled(!userInput.isBlank());  // Enable/disable based on input
    }
}
