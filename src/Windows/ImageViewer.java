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

    public ImageViewer() throws HeadlessException {
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setPreferredSize(new Dimension(400, 400));
        this.setSize(this.getPreferredSize());

        this.initViewer();
    }

    private void initViewer() {


    }


}
