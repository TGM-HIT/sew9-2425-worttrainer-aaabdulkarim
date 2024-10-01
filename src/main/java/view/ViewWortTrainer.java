package view;

import model.StatistikManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewWortTrainer extends JFrame{

    private JLabel richtig = new JLabel("+");
    private JLabel falsch = new JLabel("-");
    private JLabel insgesamt = new JLabel();

    private ImageIcon imageIcon = new ImageIcon();

    private JButton speicherButton = new JButton();
    private JButton ladeButton = new JButton();


    public ViewWortTrainer() {
        setTitle("Wort Trainer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName() + " - " + info.getClassName());
            }

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

        this.add(statistikPanel());



        setVisible(true);

    }


    /**
     * Das ist ein JPanel für die Statistikanzeige
     *
     * @return JPanel
     */
    public JPanel statistikPanel(){
        JPanel statistikPanel = new JPanel();

        richtig.setText("+");
        insgesamt.setText("");
        falsch.setText("-");

        richtig.setBackground(Color.green);
        falsch.setBackground(Color.red);

        statistikPanel.add(richtig);
        statistikPanel.add(insgesamt);
        statistikPanel.add(falsch);

        return statistikPanel;
    }

    /**
     * Das ist ein JPanel für die mittlere Anzeige, dabei sieht man das Image basierend auf einer BildURL
     * und ein Button für das nächste WortPaar
     * @return JPanel
     */
    public JPanel midPanel(){
        JPanel midPanel = new JPanel();

        try {
            JLabel imageLabel = new JLabel(imageIcon);

            midPanel.add(imageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return midPanel;
    }


    /**
     * Das ist ein JPanel für die Optionen bzw. Einstellungen. Man kann Speichern und Laden.
     * @return
     */
    public JPanel optionsPanel(){
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(this.speicherButton);
        optionsPanel.add(this.ladeButton);

        return optionsPanel;
    }


    /**
     * Setzt die Statistiken in die Labels
     *
     * @param sm StatistikManager enthält alle Daten
     */
    public void setStatistik(StatistikManager sm){
        this.richtig.setText("+" + sm.getAnzahlRichtig());
        this.falsch.setText("-" + sm.getAnzahlFalsch());
        this.insgesamt.setText("" + sm.getInsgesamt());
    }

    /**
     * Setzt die URL vom Bild
     * @param url String
     * @throws MalformedURLException
     */
    public void setBildUrl(String url) throws MalformedURLException{
        // Falls das nicht funktioniert muss das Image Label neu gesetzt werden
        this.imageIcon = new ImageIcon(new URL(url));
    }

    /**
     * Setzt jede Komponenten neu auf.
     */
    public void reset(){
        richtig = new JLabel("+");
        falsch = new JLabel("-");
        insgesamt = new JLabel();

        imageIcon = new ImageIcon();

        speicherButton = new JButton();
        ladeButton = new JButton();
    }




}
