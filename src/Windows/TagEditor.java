package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stephen on 09/02/2015.
 */
public class TagEditor extends JFrame {


    public TagEditor() throws HeadlessException {
        this.setTitle("Tags");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setPreferredSize(new Dimension(400, 400));

        this.setSize(this.getPreferredSize());

        this.initTagWindow();
    }

    private JPanel initTagWindow() {
        JPanel sideBar = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = c.gridy = 0;
        c.gridheight = 10;
        c.gridwidth = 10;


        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 8;
        c.gridy = 3;
        JButton addButton = new JButton();
        addButton.setText("Add a Tag");
        addButton.addActionListener(new addButtonListener());

        sideBar.add(addButton, c);

        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 8;
        c.gridy = 4;
        JButton saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.addActionListener(new saveButtonListener());

        sideBar.add(saveButton, c);

        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 8;
        c.gridy = 5;
        JButton deleteButton = new JButton();
        deleteButton.setText("Deletes Tag");
        deleteButton.addActionListener(new deleteButtonListener());

        sideBar.add(deleteButton, c);

        return sideBar;

    }

    private class addButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

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
}
