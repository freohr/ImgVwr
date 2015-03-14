package Windows.Utils;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalButton extends JButton {
    private String localizationKey;


    // Constructors withtout text
    public InternationalButton() {

    }

    public InternationalButton(Icon icon) {
        super(icon);
    }

    public InternationalButton(Action a) {
        super(a);
    }

    // Constructors with text
    public InternationalButton(String key, Icon icon) {
        super(icon);

        ResourceBundle rb = ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale());

        this.setText(rb.getString(key));
        this.setLocalizationKey(key);
    }

    public InternationalButton(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale());

        this.setText(rb.getString(key));
        this.setLocalizationKey(key);
    }

    public String getLocalizationKey() {
        return localizationKey;
    }

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("LabelBundle", Controller.getCurrentLocale()).getString("key"));
    }
}
