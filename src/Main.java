import javax.swing.*;

import Windows.Gallery;

public class Main {

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){

        }

       Gallery galerie = new Gallery();

        JLabel hello = new JLabel("Hello World!");

        galerie.add(hello);

        galerie.setVisible(true);
    }
}