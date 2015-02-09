import javax.swing.*;
import Gallery.*;

public class Main {

    public static void main(String[] args) {
       Gallery galerie = new Gallery();

        JLabel hello = new JLabel("Hello World!");

        galerie.add(hello);

        galerie.setVisible(true);
    }
}