package Windows;

import Controler.Controller;
import Model.Image;
import Resources.Languages;
import Windows.International.*;
import org.imgscalr.Scalr;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by stephen on 09/02/2015.
 */
public class Gallery extends JFrame implements Observer {
    public Controller controller;

    private static final String localizationKey = "gallery";

    public JScrollPane ImagePanel;
    public JScrollPane TagPane;
    public JFileChooser ImageChooser;

    private ArrayList<InternationalComponent> components = new ArrayList<>();

    private JButton PropertiesButton;
    private JButton TagsButtons;

    private Border grayBorder;
    private Border blackBorder;

    private GalleryThumbnail selectedThumbnail;

    public Gallery(Controller controller) throws HeadlessException {

        this.controller = controller;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(localizationKey));
        this.setSize(800, 600);

        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(400, 300));

        this.initGallery();

        this.addComponentListener(new ResizeListener());

        this.grayBorder = BorderFactory.createEtchedBorder(Color.gray, Color.LIGHT_GRAY);
        this.blackBorder = BorderFactory.createEtchedBorder(Color.black, Color.gray);
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
        constraints.weighty = 1;

        constraints.insets = new Insets(5, 5, 5, 5);

        // Scrollpane
        constraints.gridx = constraints.gridy = 0;
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.weightx = 0.9;


        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setLayout(new ScrollPaneLayout());

        scrollPane.setBorder(grayBorder);

        scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.ImagePanel = scrollPane;

        InternationalButton openFilesButton = new InternationalButton("open");
        openFilesButton.setAction(new OpenFileListener("open"));

        constraints.insets = new Insets(10, 10, 10, 10);
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        buttonPanel.add(openFilesButton, constraints);

        scrollPane.add(buttonPanel);

        scrollPane.revalidate();
        scrollPane.repaint();

        constraints.insets = new Insets(5, 5, 5, 5);

        pane.add(scrollPane, constraints);

        // SideBar

        JPanel sideBar = initSideBar();

        constraints.gridx = 1;
        constraints.weightx = 0.1;

        pane.add(sideBar, constraints);

        pane.setBorder(grayBorder);

        pane.setVisible(true);
        this.add(pane);
    }

    private void initMenu() {
        // MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichiers
        InternationalMenu menu = new InternationalMenu("file");

        InternationalMenuItem loadFiles = new InternationalMenuItem("open", new OpenFileListener("open"));
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

        InternationalRadioMenuItem jpJP = new InternationalRadioMenuItem(true, "japanese", Languages.JAPANESE, new ChangeLanguageListener());
        langues.add(jpJP);
        groupLangues.add(jpJP);

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
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Panel Tags

        JPanel tagPanel = new JPanel(new GridBagLayout());

        // Label "by tag"
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.weighty = 0.1;

        InternationalLabel filters = new InternationalLabel("filterTags");

        components.add(filters);

        tagPanel.add(filters, constraints);

        // Panel "choose tags"
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;

        JScrollPane tagPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tagPane.setPreferredSize(new Dimension(100, 200));
        tagPane.setSize(tagPane.getPreferredSize());
        this.TagPane = tagPane;

        constraints.weighty = 2;

        tagPanel.add(tagPane, constraints);

        constraints.weighty = 1.5;

        side.add(tagPanel, constraints);

        constraints.weighty = 0.5;

        constraints.insets = new Insets(15, 15, 15, 15);
        constraints.fill = GridBagConstraints.BOTH;

        //Edit Properties
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 3;
        InternationalButton properties = new InternationalButton("editProperties");
        properties.addActionListener(new PropertiesButtonListener());
        properties.setEnabled(false);

        PropertiesButton = properties;

        components.add(properties);

        side.add(properties, constraints);

        //Edit Tags
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.gridy = 4;
        InternationalButton tags = new InternationalButton("editTags");
        tags.setEnabled(false);
        tags.addActionListener(new TagButtonListener());

        TagsButtons = tags;

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

        tileConstraints.gridheight = 1;
        tileConstraints.gridwidth = 1;
        tileConstraints.fill = GridBagConstraints.BOTH;

        JPanel previews = new JPanel(previewLayout);
        previews.setMinimumSize(ImagePanel.getSize());
        previews.setSize(ImagePanel.getSize());
        previews.setPreferredSize(new Dimension(ImagePanel.getWidth(), ImagePanel.getHeight() * 3));

        ImagePanel.setLayout(new ScrollPaneLayout());
        ImagePanel.setBorder(blackLine);

        ArrayList<Image> images = controller.getImages();

        images.stream().forEach(i -> {
            JPanel imgTile = new GalleryThumbnail(new GridBagLayout(), i.getTitle());

            imgTile.setSize(new Dimension(200, 150));
            imgTile.setMinimumSize(new Dimension(200, 150));
            imgTile.setPreferredSize(new Dimension(200, 150));
            imgTile.setBorder(grayBorder);
            imgTile.addMouseListener(new ThumbnailHoverListener());

            JLabel img = new JLabel(new ImageIcon(Scalr.resize(i.getImage(), 100)));
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

        this.setTitle(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(localizationKey));
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
        public OpenFileListener(String name) {
            super(name);
        }

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
            if (selectedThumbnail != null) {
                TagEditor tagEditor = new TagEditor(selectedThumbnail.getImageTitle(), controller);

                tagEditor.setVisible(true);
            }

        }
    }

    private class PropertiesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (selectedThumbnail != null) {
                //Image image = controller.getImage(selectedThumbnail.getImageTitle());

                ImageViewer imageViewer = new ImageViewer(selectedThumbnail.getImageTitle(), controller);

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

            PropertiesButton.setEnabled(true);
            TagsButtons.setEnabled(true);

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
