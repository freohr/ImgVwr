package Windows;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by stephen on 16/02/2015.
 */
public class FileChooser extends JFileChooser {

    public FileChooser() {
        super();


        this.setMultiSelectionEnabled(true);
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        this.setFileFilter(new ImageFilter());
    }

    private class ImageFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = FilenameUtils.getExtension(f.getName());

            if (extension != null) {
                if (extension.equals("gif") ||
                        extension.equals("jpeg") ||
                        extension.equals("jpg") ||
                        extension.equals("bmp") ||
                        extension.equals("png")) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

        @Override
        public String getDescription() {
            return "Images";
        }
    }
}
