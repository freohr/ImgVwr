package Windows;

import Model.ImageList;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by stephen on 09/02/2015.
 */
public class Gallery extends JFrame implements Observer{
    public ImageList model;

    public JScrollPane ImagePanel;
    public JScrollPane TagPane;

    public Gallery(ImageList Model) throws HeadlessException {

        this.model = Model;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gallerie");
        this.setSize(800, 600);

        this.initGallery();
    }

    private void initGallery()
    {
        JPanel pane = new JPanel(new GridBagLayout());
        pane.setPreferredSize(this.getSize());
        pane.setMinimumSize(this.getMinimumSize());

        GridBagConstraints constraints = new GridBagConstraints();


        constraints.fill = GridBagConstraints.BOTH;


        // Scrollpane
        constraints.gridx = constraints.gridy = 0;
        constraints.gridheight = 5;
        constraints.gridwidth = 4;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(400, 400);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        this.ImagePanel = scrollPane;

        pane.add(scrollPane, constraints);

        // Filters

        // Label "by tag"
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridx = 4;
        constraints.gridy = 0;

        JLabel filters = new JLabel("Filter par tag");

        pane.add(filters, constraints);

        // Panel "choose tags"
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;

        JScrollPane tagPane = new JScrollPane();
        tagPane.setPreferredSize(new Dimension(100, 200));
        tagPane.setSize(tagPane.getPreferredSize());
        this.TagPane = tagPane;

        pane.add(tagPane, constraints);


        //Edit Properties
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridx = 4;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JButton properties = new JButton();
        properties.setText("Edit Properties");

        pane.add(properties, constraints);

        //Edit Tags
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JButton tags = new JButton();
        tags.setText("Edit Tags");

        pane.add(tags, constraints);

        pane.setVisible(true);
        this.add(pane);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
