package control;

import model.WortPaar;
import model.WortTrainer;
import persistance.SpeichernLadenJson;
import persistance.SpeichernLadenStrategie;
import view.ViewWortTrainer;

import javax.swing.*;
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
public class ControlWortTrainer implements ActionListener{

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

    public void setBildUrl() {
        WortPaar aktuellesWortPaar = this.model.getAktuellesWort();

        try {
            this.view.setBildUrl(aktuellesWortPaar.getBildURL());
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
