package Windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stephen on 09/02/2015.
 */

/**
 * Modified by Rémy on 09/02/2015.
 */
public class ImageViewer extends JFrame {

    private String ImageName;

    public ImageViewer(String Image) throws HeadlessException {
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.ImageName = Image;

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
