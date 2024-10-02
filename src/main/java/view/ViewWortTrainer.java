package view;

import control.ControlWortTrainer;
import model.StatistikManager;

import javax.swing.*;
import java.awt.*;

import java.net.MalformedURLException;
import java.net.URL;

public class ViewWortTrainer extends JFrame {

    private JLabel richtig = new JLabel("+");
    private JLabel falsch = new JLabel("-");
    private JLabel insgesamt = new JLabel("||");

    private ImageIcon imageIcon = new ImageIcon();
    private JLabel imageLabel = new JLabel(imageIcon);

    private JButton nextButton = new JButton("Naechstes Wort");

    private JButton speicherButton = new JButton("Speichern");
    private JButton ladeButton = new JButton("Laden");

    private JTextField textField = new JTextField();

    /**
     * Konstruktor der View. Hier wird jedes Panel zusamengebunden
     * @param control
     */
    public ViewWortTrainer(ControlWortTrainer control) {
        setTitle("Wort Trainer");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName() + " - " + info.getClassName());
            }

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }


        this.add(statistikPanel(), BorderLayout.PAGE_START);
        this.add(speicherButton, BorderLayout.LINE_START);
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(ladeButton, BorderLayout.LINE_END);
        this.add(optionsPanel(), BorderLayout.PAGE_END);

        speicherButton.addActionListener(control);
        speicherButton.setActionCommand("save");

        ladeButton.addActionListener(control);
        ladeButton.setActionCommand("load");

        nextButton.addActionListener(control);
        nextButton.setActionCommand("next");


        setVisible(true);

    }


    /**
     * Das ist ein JPanel für die Statistikanzeige
     *
     * @return JPanel
     */
    public JPanel statistikPanel() {
        JPanel statistikPanel = new JPanel();

        richtig.setText("+");
        insgesamt.setText("||");
        falsch.setText("-");

        richtig.setBackground(Color.green);
        falsch.setBackground(Color.red);

        statistikPanel.add(richtig);
        statistikPanel.add(insgesamt);
        statistikPanel.add(falsch);

        return statistikPanel;
    }



    /**
     * Erstellt das Options Panel. Hier kann man mit dem User
     *
     * @return JPanel
     */
    public JPanel optionsPanel() {
        JPanel optionsPanel = new JPanel();
        this.textField.setPreferredSize(new Dimension(330, 75));
        optionsPanel.add(this.textField);
        optionsPanel.add(this.nextButton);

        return optionsPanel;
    }


    /**
     * Setzt die Statistiken in die Labels
     *
     * @param sm StatistikManager enthält alle Daten
     */
    public void setStatistik(StatistikManager sm) {
        this.richtig.setText("+" + sm.getAnzahlRichtig());
        this.falsch.setText("-" + sm.getAnzahlFalsch());
        this.insgesamt.setText("|" + sm.getInsgesamt() + "|");
    }

    /**
     * Setzt die URL vom Bild
     *
     * @param url String
     * @throws MalformedURLException
     */
    public void setBildUrl(String url) throws MalformedURLException {
        // Falls das nicht funktioniert muss das Image Label neu gesetzt werden
        this.imageIcon = new ImageIcon(new URL(url));
        this.imageLabel.setIcon(imageIcon);
    }

    /**
     * Setzt jede Komponenten neu auf.
     */
    public void reset() {
        richtig = new JLabel("+");
        falsch = new JLabel("-");
        insgesamt = new JLabel("||");

        imageIcon = new ImageIcon();
        imageLabel = new JLabel();

        speicherButton = new JButton("Speichern");
        ladeButton = new JButton("Laden");
    }


    /**
     * Gibt den User Input im String Format zurück
     * @return String
     */
    public String getUserInput() {
        return this.textField.getText();
    }
}
