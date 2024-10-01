package control;

import model.WortTrainer;
import persistance.SpeichernLadenJson;
import persistance.SpeichernLadenStrategie;
import view.ViewWortTrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlWortTrainer implements ActionListener{

    private WortTrainer model;
    private JFrame view;

    private SpeichernLadenStrategie speicherStrategie;

    /**
     * Controller
     */
    public ControlWortTrainer(){
        this.model = new WortTrainer();
        this.view = new ViewWortTrainer();
        this.speicherStrategie = new SpeichernLadenJson();
    }

    /**
     *
     * @param model
     * @param view
     * @param speicherStrategie
     */
    public ControlWortTrainer(WortTrainer model, ViewWortTrainer view, SpeichernLadenStrategie speicherStrategie){
        this.model = model;
        this.view = view;
        this.speicherStrategie = speicherStrategie;
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public void setSpeicherPfad(String pfad){
    }

}
