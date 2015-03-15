package Windows;

import Controler.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 09/02/2015.
 */

/**
 * Modified by Rémy on 09/02/2015.
 */
public class ImageViewer extends JFrame {

    private String ImageName;
    private Controller controller;
    private static final String localizationKey = "details";

    public ImageViewer(String Image, Controller controller) throws HeadlessException {
        this.setTitle(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(localizationKey));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.ImageName = Image;
        this.controller = controller;

        this.setPreferredSize(new Dimension(400, 400));
        this.setSize(this.getPreferredSize());

        this.initViewer();
    }

    private void initViewer() {


    }

    private void initDtails() {

    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }
}
