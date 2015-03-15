package Windows.International;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalButton extends JButton implements InternationalComponent {
    private String localizationKey;

    // Constructors with text
    public InternationalButton(String key, Icon icon) {
        super(icon);

        this.setLocalizationKey(key);
        this.updateText();
    }

    public InternationalButton(String key) {
        this.setLocalizationKey(key);
        this.updateText();
    }

    public String getLocalizationKey() {
        return localizationKey;
    }

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(key == null || key == "" ? "default" : key));
    }

    public void updateText() {
        if (this.localizationKey != null)
            this.setText(this.localizationKey);
    }


}
