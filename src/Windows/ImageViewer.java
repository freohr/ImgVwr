package Windows;

import Controler.Controller;
import Model.Image;
import Windows.International.InternationalLabel;
import org.imgscalr.Scalr;

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

        this.setPreferredSize(new Dimension(800, 600));
        this.setSize(this.getPreferredSize());

        this.initViewer();
    }

    private void initViewer() {
        JPanel back = new JPanel(new GridBagLayout());
        back.setMinimumSize(this.getMinimumSize());
        back.setPreferredSize(this.getPreferredSize());

        JPanel imagePanel = new JPanel();

        Image image = controller.getImage(getImageName());
        JLabel imageView = new JLabel(new ImageIcon(Scalr.resize(image.getImage(), 500)));

        imagePanel.add(imageView);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridwidth = constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weighty = 0.7;

        back.add(imagePanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.3;

        back.add(initControls(), constraints);

        this.add(back);
    }

    private JPanel initControls() {
        JPanel controls = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = constraints.gridwidth = 1;


        // Panneau titre (1/3)
        JPanel titlePanel = new JPanel(new GridBagLayout());
        InternationalLabel imageTitleLabel = new InternationalLabel("imageTitle");

        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.2;

        titlePanel.add(imageTitleLabel, constraints);

        JLabel imageTitle = new JLabel(this.getImageName());

        constraints.gridy = 1;
        constraints.weighty = 0.8;

        titlePanel.add(imageTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;

        controls.add(titlePanel, constraints);

        // Panneau Description (2/3)

        JPanel descriptionPanel = new JPanel(new GridBagLayout());

        InternationalLabel descriptionLabel = new InternationalLabel("description");
        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.2;

        descriptionPanel.add(descriptionLabel, constraints);

        JTextArea descriptionTextArea = new JTextArea(controller.getImage(getImageName()).getDescription());

        constraints.gridy = 1;
        constraints.weighty = 0.8;

        descriptionPanel.add(descriptionTextArea, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;

        controls.add(descriptionPanel, constraints);

        // Panneau Tags (3/3)

        JPanel tagPanel = new JPanel(new GridBagLayout());

        InternationalLabel tagLabel = new InternationalLabel("tags");

        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.2;
        tagPanel.add(tagLabel, constraints);

        JPanel tagList = new JPanel();
        tagList.add(new InternationalLabel("tagList"));

        constraints.gridy = 1;
        constraints.weighty = 0.8;
        tagPanel.add(tagList, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;

        controls.add(tagPanel, constraints);

        return controls;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }
}
