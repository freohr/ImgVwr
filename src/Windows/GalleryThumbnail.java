package Windows;

import Windows.Utils.Border;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stephen on 07/03/2015.
 */
public class GalleryThumbnail extends JPanel {
    public String ImageTitle;
    public boolean selected;

    public GalleryThumbnail(LayoutManager layout, String imageTitle) {
        super(layout);

        this.ImageTitle = imageTitle;
    }

    public void setBorder(Border border) {
        switch (border) {
            case BLACK:
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                break;

            case GRAY:
                this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                break;

            case RED:
                this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                break;
        }
    }

    public String getImageTitle() {
        return ImageTitle;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected) {
            this.setBorder(Border.RED);
        } else {
            this.setBorder(Border.GRAY);
        }
    }
}
