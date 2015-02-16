package Windows;

import Controler.Controller;
import Model.Image;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by stephen on 09/02/2015.
 */
public class Gallery extends JFrame implements Observer {
    public Controller controller;

    public JScrollPane ImagePanel;
    public JScrollPane TagPane;
    public JFileChooser ImageChooser;

    public Gallery(Controller controller) throws HeadlessException {

        this.controller = controller;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gallerie");
        this.setSize(800, 600);

        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(400, 300));

        this.initGallery();
    }

    // Gallery Window Initilization

    private void initGallery() {
        JPanel pane = new JPanel(new GridBagLayout());
        pane.setPreferredSize(this.getSize());
        pane.setMinimumSize(this.getMinimumSize());

        initMenu();
        initChooser();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;

        // Scrollpane
        constraints.gridx = constraints.gridy = 0;
        constraints.gridheight = 5;
        constraints.gridwidth = 4;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(400, 400);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.ImagePanel = scrollPane;

        pane.add(scrollPane, constraints);

        // SideBar

        JPanel sideBar = initSideBar();

        constraints.gridy = 0;
        constraints.gridx = 4;
        constraints.gridheight = 5;
        constraints.fill = GridBagConstraints.BOTH;

        pane.add(sideBar, constraints);

        pane.setVisible(true);
        this.add(pane);
    }

    private void initMenu() {
        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Files");
        JMenuItem loadFiles = new JMenuItem(new OpenFileListener());
        loadFiles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        menu.add(loadFiles);

        menuBar.add(menu);

        menuBar.setVisible(true);

        this.setJMenuBar(menuBar);
    }

    private void initChooser() {
        this.ImageChooser = new FileChooser();
    }

    private JPanel initSideBar() {

        JPanel side = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        // Contraintes communes
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;


        // Label "by tag"
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 0;

        JLabel filters = new JLabel("Filter by tag");

        side.add(filters, constraints);

        // Panel "choose tags"
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;

        JScrollPane tagPane = new JScrollPane();
        tagPane.setPreferredSize(new Dimension(100, 200));
        tagPane.setSize(tagPane.getPreferredSize());
        this.TagPane = tagPane;

        side.add(tagPane, constraints);

        //Edit Properties
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 3;
        JButton properties = new JButton();
        properties.setText("Edit Properties");
        properties.addActionListener(new PropertiesButtonListener());

        side.add(properties, constraints);

        //Edit Tags
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 4;
        JButton tags = new JButton();
        tags.setText("Edit Tags");
        tags.addActionListener(new TagButtonListener());

        side.add(tags, constraints);

        return side;
    }

    @Override
    public void update(Observable o, Object arg) {
        updatePreview();
        updateTags();
    }

    private void updatePreview() {
        ImagePanel.removeAll();

        GridLayout previewLayout = new GridLayout(0, 3, 20, 20);
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);

        JPanel previews = new JPanel(previewLayout);
        previews.setAutoscrolls(true);
        previews.setSize(ImagePanel.getSize());

        ImagePanel.add(previews);

        ImagePanel.setLayout(new ScrollPaneLayout());
        ImagePanel.setBorder(blackLine);

        ArrayList<Image> images = controller.getImages();

        images.stream().forEach(i -> {
            JPanel imgTile = new JPanel(new GridLayout(2, 1));

            imgTile.setSize(new Dimension(100, 100));
            imgTile.setMinimumSize(new Dimension(100, 100));
            imgTile.setMaximumSize(new Dimension(100, 100));
            imgTile.setBorder(blackLine);

            JLabel img = new JLabel(new ImageIcon(i.getImage().getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH)));
            img.setSize(new Dimension(100, 80));

            JLabel title = new JLabel(i.getTitle());

            imgTile.add(img);
            imgTile.add(title);

            previews.add(imgTile);
            previews.repaint();

            ImagePanel.revalidate();
            ImagePanel.repaint();
        });
    }

    private void updateTags() {

    }

    // Events Listeners

    // Menu

    private class OpenFileListener extends AbstractAction {

        public OpenFileListener() {
            super("Load Pictures");
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            int returnVal = ImageChooser.showOpenDialog(Gallery.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] arrayFile = ImageChooser.getSelectedFiles();

                try {
                    controller.importImages(arrayFile);
                } catch (Exception ex) {
                }
            }
        }
    }

    // Buttons

    private class TagButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TagEditor tagEditor = new TagEditor();

            tagEditor.setVisible(true);
        }
    }

    private class PropertiesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ImageViewer imageViewer = new ImageViewer();

            imageViewer.setVisible(true);
        }
    }
}
