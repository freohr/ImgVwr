package Windows;

import Controler.Controller;
import Model.Image;
import Windows.International.InternationalButton;
import Windows.International.InternationalLabel;
import org.imgscalr.Scalr;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
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

    private JTextArea descriptionArea;

    public ImageViewer(String Image, Controller controller) throws HeadlessException {
        this.setTitle(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(localizationKey));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.ImageName = Image;
        this.controller = controller;

        this.setPreferredSize(new Dimension(800, 600));
        this.setSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());

        this.initViewer();
    }

    private void initViewer() {
        JPanel back = new JPanel(new GridBagLayout());
        back.setMinimumSize(this.getMinimumSize());
        back.setPreferredSize(this.getPreferredSize());

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setPreferredSize(this.getPreferredSize());

        Image image = controller.getImage(getImageName());
        JLabel imageView = new JLabel(new ImageIcon(Scalr.resize(image.getImage(), (int) (800 * 0.6))));



        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridwidth = constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;

        imagePanel.add(imageView, constraints);
        imagePanel.setBorder(BorderFactory.createEtchedBorder());

        constraints.weighty = 0.7;


        back.add(imagePanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.3;

        JPanel controls = initControls();
        controls.setPreferredSize(this.getPreferredSize());

        back.add(controls, constraints);

        this.add(back);
    }

    private JPanel initControls() {
        JPanel controls = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Insets insets5 = new Insets(5, 5, 5, 5);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = constraints.gridwidth = 1;
        constraints.insets = insets5;
        Border etched = BorderFactory.createEtchedBorder();


        // Panneau titre (1/3)
        JPanel titlePanel = new JPanel(new GridBagLayout());
        InternationalLabel imageTitleLabel = new InternationalLabel("imageTitle");

        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.1;

        titlePanel.add(imageTitleLabel, constraints);

        JLabel imageTitle = new JLabel(this.getImageName());

        constraints.gridy = 1;
        constraints.weighty = 1.5;

        titlePanel.add(imageTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;

        titlePanel.setBorder(etched);
        controls.add(titlePanel, constraints);

        // Panneau Description (2/3)

        JPanel descriptionPanel = new JPanel(new GridBagLayout());

        InternationalLabel descriptionLabel = new InternationalLabel("description");
        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.1;

        descriptionPanel.add(descriptionLabel, constraints);

        JTextArea descriptionTextArea = new JTextArea(controller.getImage(getImageName()).getDescription());
        descriptionArea = descriptionTextArea;
        descriptionTextArea.setBorder(etched);

        constraints.gridy = 1;
        constraints.weighty = 1.5;

        descriptionPanel.add(descriptionTextArea, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1.5;


        descriptionPanel.setBorder(etched);
        controls.add(descriptionPanel, constraints);

        // Panneau Tags (3/3)

        JPanel tagPanel = new JPanel(new GridBagLayout());

        InternationalLabel tagLabel = new InternationalLabel("tags");

        constraints.gridy = constraints.gridx = 0;
        constraints.weighty = 0.1;
        tagPanel.add(tagLabel, constraints);

        JPanel tagList = new JPanel(new GridBagLayout());

        HashSet<String> tagSet = controller.getImage(getImageName()).getTags();

        tagSet.stream().forEach(tag -> tagList.add(new JLabel(tag), constraints));

        constraints.gridy = 1;
        constraints.weighty = 1.5;
        tagList.setBorder(etched);
        tagPanel.add(tagList, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;

        tagPanel.setBorder(etched);
        controls.add(tagPanel, constraints);

        // Panneau Boutons (4/3)

        JPanel controlButtonsPanal = new JPanel(new GridBagLayout());

        Dimension buttonSize = new Dimension(50, 10);
        Insets insets50 = new Insets(15, 15, 15, 15);

        InternationalButton saveButton = new InternationalButton("save");
        saveButton.addActionListener(new SaveButtonListener());
        saveButton.setSize(buttonSize);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets50;

        controlButtonsPanal.add(saveButton, constraints);

        InternationalButton cancelButton = new InternationalButton("cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        cancelButton.setSize(buttonSize);

        constraints.gridx = 0;
        constraints.gridy = 1;

        controlButtonsPanal.add(cancelButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weighty = constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets5;

        controls.add(controlButtonsPanal, constraints);

        controls.setBorder(etched);
        return controls;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.saveImageDescription(ImageName, descriptionArea.getText());
            } catch (Exception ex) {

            }
        }
    }

    private class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container frame = ((InternationalButton) e.getSource()).getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();
        }
    }
}
