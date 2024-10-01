package view;

import javax.swing.*;

public class ViewWortTrainer extends JFrame{

    public ViewWortTrainer() {
        setTitle("Wort Trainer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName() + " - " + info.getClassName());
            }

            // Set a specific Look and Feel (e.g., Nimbus)
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }



        setVisible(true);

    }

    public JPanel statistikPanel(){
        this.
    }

}
