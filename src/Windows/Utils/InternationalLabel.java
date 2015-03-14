package Windows.Utils;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalLabel extends JLabel {
    private String localizationKey;

    // Constructors with key & locale for text
    public InternationalLabel(String key, Icon icon, int horizontalAlignment) {
        this(icon, horizontalAlignment);

        ResourceBundle rb = ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale());

        this.setText(rb.getString(key));

        this.localizationKey = key;
    }

    public InternationalLabel(String key, int horizontalAlignment) {
        ResourceBundle rb = ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale());

        this.setText(rb.getString(key));
        this.setHorizontalAlignment(horizontalAlignment);

        this.localizationKey = key;
    }

    public InternationalLabel(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale());

        this.setText(rb.getString(key));

        this.localizationKey = key;
    }

    // Constructors without text
    public InternationalLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public InternationalLabel(Icon image) {
        super(image);
    }

    public InternationalLabel() {
    }


    // Getters
    public String getLocalizationKey() {
        return localizationKey;
    }

    // Setters

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale()).getString("key"));
    }
}
