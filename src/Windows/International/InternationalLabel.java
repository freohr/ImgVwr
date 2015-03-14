package Windows.International;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalLabel extends JLabel implements InternationalComponent {
    private String localizationKey;

    // Constructors with key & locale for text
    public InternationalLabel(String key, Icon icon, int horizontalAlignment) {
        super(icon, horizontalAlignment);

        this.localizationKey = key;
        this.updateText();
    }

    public InternationalLabel(String key, int horizontalAlignment) {
        this.setHorizontalAlignment(horizontalAlignment);

        this.localizationKey = key;
        this.updateText();
    }

    public InternationalLabel(String key) {
        this.localizationKey = key;
        this.updateText();
    }

    // Getters
    public String getLocalizationKey() {
        return localizationKey;
    }

    // Setters

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    // Methods

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(key));
    }

    public void updateText() {
        if (this.localizationKey != null)
            this.setText(this.localizationKey);
    }
}
