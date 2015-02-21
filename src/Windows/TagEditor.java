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

        initTagWindow();
    }

    private void initTagWindow() {
        JPanel sideBar = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridheight = 0;
        c.gridwidth = 0;
    }
}
