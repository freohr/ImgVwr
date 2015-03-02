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

        initTagWindow();
    }

    private void initTagWindow() {
        JPanel sideBar = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridheight = 0;
        c.gridwidth = 0;

        JButton addButton = new JButton();
        addButton.setText("Add a Tag");
        addButton.addActionListener(new addButtonListener());

        JButton saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.addActionListener(new saveButtonListener());

        JButton deleteButton = new JButton();
        deleteButton.setText("Deletes Tag");
        deleteButton.addActionListener(new deleteButtonListener());

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
