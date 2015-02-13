import Controler.Controller;
import Model.ImageList;
import Windows.Gallery;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        ImageList model = new ImageList();

        Controller controller = new Controller(model);

        Gallery galerie = new Gallery(controller);

        model.addObserver(galerie);

        galerie.setVisible(true);
    }
}