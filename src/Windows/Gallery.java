package Windows;

import Controler.Controller;
import Model.Image;
import Resources.Languages;
import Windows.International.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
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

    private ArrayList<InternationalComponent> components = new ArrayList<>();

    private Border grayBorder;
    private Border blackBorder;

    private GalleryThumbnail selectedThumbnail;

    public Gallery(Controller controller) throws HeadlessException {

        this.controller = controller;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gallerie");
        this.setSize(800, 600);

        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(400, 300));

        this.initGallery();

        this.addComponentListener(new ResizeListener());

        this.grayBorder = BorderFactory.createLineBorder(Color.gray, 1);
        this.blackBorder = BorderFactory.createLineBorder(Color.black, 2);
    }

    // Gallery Window Initilization

    private void initGallery() {
        JPanel pane = new JPanel(new GridBagLayout());
        pane.setPreferredSize(this.getSize());
        pane.setMinimumSize(this.getMinimumSize());

        initMenu();
        initChooser();

        GridBagConstraints constraints = new GridBagConstraints();
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;

        // Scrollpane
        constraints.gridx = constraints.gridy = 0;
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.weightx = 0.9;


        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        /*scrollPane.setSize(500, 500);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        */
        scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.ImagePanel = scrollPane;

        pane.add(scrollPane, constraints);

        // SideBar

        JPanel sideBar = initSideBar();

        constraints.gridx = 1;
        constraints.weightx = 0.1;

        pane.add(sideBar, constraints);

        pane.setVisible(true);
        this.add(pane);
    }

    private void initMenu() {
        // MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichiers
        InternationalMenu menu = new InternationalMenu("file");

        InternationalMenuItem loadFiles = new InternationalMenuItem("open", new OpenFileListener());
        loadFiles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        InternationalMenuItem close = new InternationalMenuItem("exit", new ExitAppListener());

        menu.add(loadFiles);
        menu.add(close);

        menuBar.add(menu);

        // Menu Langues
        InternationalMenu langues = new InternationalMenu("languages");
        ButtonGroup groupLangues = new ButtonGroup();

        InternationalRadioMenuItem frFR = new InternationalRadioMenuItem("french", Languages.FRENCH, new ChangeLanguageListener());
        langues.add(frFR);
        groupLangues.add(frFR);

        InternationalRadioMenuItem enUS = new InternationalRadioMenuItem(true, "english", Languages.ENGLISH, new ChangeLanguageListener());
        langues.add(enUS);
        groupLangues.add(enUS);

        menuBar.add(langues);

        // Ajout dans l'array de localization
        components.add(menu);
        components.add(loadFiles);
        components.add(close);
        components.add(langues);
        components.add(frFR);
        components.add(enUS);

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

        InternationalLabel filters = new InternationalLabel("filterTags");

        components.add(filters);

        side.add(filters, constraints);

        // Panel "choose tags"
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;

        JScrollPane tagPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tagPane.setPreferredSize(new Dimension(100, 200));
        tagPane.setSize(tagPane.getPreferredSize());
        this.TagPane = tagPane;

        side.add(tagPane, constraints);

        //Edit Properties
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 3;
        InternationalButton properties = new InternationalButton("editProperties");
        properties.addActionListener(new PropertiesButtonListener());

        components.add(properties);

        side.add(properties, constraints);

        //Edit Tags
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 4;
        InternationalButton tags = new InternationalButton("editTags");
        tags.addActionListener(new TagButtonListener());

        components.add(tags);

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

        JPanel loading = new JPanel();
        //loading.add(new JLabel("Chargement des images en cours"));

        ImagePanel.add(loading);
        ImagePanel.revalidate();
        ImagePanel.repaint();

        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);

        ImagePanel.setBorder(blackLine);

        GridLayout previewLayout = new GridLayout(4, 4, 20, 20);
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints tileConstraints = new GridBagConstraints();

/*        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;*/

        tileConstraints.gridheight = 1;
        tileConstraints.gridwidth = 1;
        tileConstraints.fill = GridBagConstraints.BOTH;

        JPanel previews = new JPanel(previewLayout);
        previews.setMinimumSize(ImagePanel.getSize());
        previews.setSize(ImagePanel.getSize());
        previews.setPreferredSize(new Dimension(ImagePanel.getWidth(), ImagePanel.getHeight() * 3));

        //previews.setSize(ImagePanel.getSize().width, ImagePanel.getHeight());

        ImagePanel.setLayout(new ScrollPaneLayout());
        ImagePanel.setBorder(blackLine);

        ArrayList<Image> images = controller.getImages();

        images.stream().forEach(i -> {
            JPanel imgTile = new GalleryThumbnail(new GridBagLayout(), i.getTitle());

            imgTile.setSize(new Dimension(200, 150));
            imgTile.setMinimumSize(new Dimension(200, 150));
            imgTile.setPreferredSize(new Dimension(200, 150));
            imgTile.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            imgTile.addMouseListener(new ThumbnailHoverListener());

            JLabel img = new JLabel(new ImageIcon(i.getImage().getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH)));
            img.setSize(new Dimension(100, 80));

            JLabel title = new JLabel(i.getTitle());

            tileConstraints.weighty = 1.0;
            tileConstraints.gridy = 0;
            imgTile.add(img, tileConstraints);

            tileConstraints.weighty = 0.25;
            tileConstraints.gridy = 1;
            imgTile.add(title, tileConstraints);

            previews.add(imgTile);

        });

        previews.repaint();
        ImagePanel.add(previews);

        ImagePanel.revalidate();
        ImagePanel.repaint();
    }

    private void updateTags() {

    }

    private void updateLabels() {
        components.stream().forEach(c -> {
            c.updateText();
            c.repaint();
        });
    }

    // Events Listeners

    // Frame

    private class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent evt) {
            Component c = (Component) evt.getSource();

            Gallery glry = (Gallery) c;

            glry.updatePreview();
        }

    }

    // Menu

    private class OpenFileListener extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int returnVal = ImageChooser.showOpenDialog(Gallery.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] arrayFile = ImageChooser.getSelectedFiles();

                try {
                    controller.importImages(arrayFile);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ImagePanel,
                            "Prolème lors du chargement des images.",
                            "Erreur de chargement",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class ChangeLanguageListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            InternationalRadioMenuItem source = (InternationalRadioMenuItem) e.getSource();

            Controller.setCurrentLocale(source.getLanguage());

            updateLabels();
        }
    }

    private class ExitAppListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
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

            if (!selectedThumbnail.equals("")) {
                Image image = controller.getImage(selectedThumbnail.getImageTitle());

                ImageViewer imageViewer = new ImageViewer(image.getTitle());

                imageViewer.setVisible(true);
            }
        }
    }

    // Thumbnails

    private class ThumbnailHoverListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            GalleryThumbnail source = (GalleryThumbnail) e.getSource();

            source.setBorder(Windows.Utils.Border.RED);


            if (selectedThumbnail != null) {
                selectedThumbnail.setSelected(false);
            }

            selectedThumbnail = source;
            selectedThumbnail.setSelected(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            GalleryThumbnail src = (GalleryThumbnail) e.getSource();

            if (selectedThumbnail != src)
                src.setBorder(Windows.Utils.Border.BLACK);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            GalleryThumbnail src = (GalleryThumbnail) e.getSource();

            if (selectedThumbnail != src)
                src.setBorder(Windows.Utils.Border.GRAY);
        }
    }
}
