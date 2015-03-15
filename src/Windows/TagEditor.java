package Windows;

import Controler.Controller;
import Windows.International.InternationalButton;
import Windows.International.InternationalLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.ResourceBundle;


/**
 * Created by stephen on 09/02/2015.
 */

/**
 * modified by remy on 12/02/2015.
 */

public class TagEditor extends JFrame {
    private static final String localizationKey = "tags";

    private final String imageName;
    private Controller controller;

    private JPanel tagList;

    public TagEditor(String imageName, Controller controller) throws HeadlessException {
        this.imageName = imageName;
        this.controller = controller;

        this.setTitle(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(localizationKey));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setPreferredSize(new Dimension(400, 400));

        this.setSize(this.getPreferredSize());

        JPanel side = initSidebar();
        JPanel tags = initTagWindow();

        JPanel container = new JPanel(new GridBagLayout());
        container.setSize(this.getSize());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridheight = c.gridwidth = 1;
        c.gridx = c.gridy = 0;

        c.weightx = 0.9;
        c.weighty = 1;
        container.add(tags, c);

        c.gridx = 1;
        c.weightx = 0.1;
        container.add(side, c);

        this.add(container);
    }

    private JPanel initTagWindow() {

        JPanel tags = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = c.gridwidth = 1;
        //1/2 panels tags

        InternationalLabel tagsLabel = new InternationalLabel("tags");
        c.gridy = c.gridx = 0;
        c.weighty = 0.2;

        tags.add(tagsLabel, c);

        JPanel tagsTextArea = new JPanel(new GridBagLayout());

        tagList = tagsTextArea;
        tagsTextArea.setBorder(BorderFactory.createEtchedBorder());

        tagsTextArea.setSize(600, 600);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;

        updateTagList();
        tags.add(tagsTextArea, c);

        Border black = BorderFactory.createLineBorder(Color.lightGray, 1);
        tags.setBorder(black);

        return tags;
    }

    private JPanel initSidebar() {
        JPanel sideBar = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.insets = new Insets(10, 10, 10, 10);

        // Add Button
        JButton addButton = new InternationalButton("addTag");
        addButton.addActionListener(new addButtonListener());

        buttonPanel.add(addButton, c);

        // Edit Button
        JButton saveButton = new InternationalButton("editTag");
        saveButton.addActionListener(new saveButtonListener());

        c.gridy = 1;
        buttonPanel.add(saveButton, c);

        // Delete Button
        JButton deleteButton = new InternationalButton("deleteTag");
        deleteButton.addActionListener(new deleteButtonListener());

        c.gridy = 2;
        buttonPanel.add(deleteButton, c);

        c.insets = new Insets(60, 0, 60, 0);

        sideBar.add(buttonPanel, c);


        return sideBar;
    }

    public String getImageName() {
        return imageName;
    }

    private void updateTagList() {

        HashSet<String> tagSet = controller.getImage(getImageName()).getTags();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = constraints.weightx = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;

        tagSet.stream().forEach(tag -> {
            constraints.gridy++;

            tagList.add(new JLabel(tag), constraints);
        });

        tagList.revalidate();
        tagList.repaint();
    }

    private class addButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String tag = JOptionPane.showInputDialog(ResourceBundle.getBundle("Resources.LabelBundle").getString("addATag"));

                controller.getImage(getImageName()).addTag(tag);

                updateTagList();
            } catch (Exception ex) {

            }
        }
    }


    private class saveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class deleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class TagClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
