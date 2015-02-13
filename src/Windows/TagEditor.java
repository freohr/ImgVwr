package Windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stephen on 09/02/2015.
 */
public class TagEditor extends JFrame {
    public TagEditor() throws HeadlessException {
        this.setTitle("Tags");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setPreferredSize(new Dimension(400, 400));

        this.setSize(this.getPreferredSize());
    }
}
