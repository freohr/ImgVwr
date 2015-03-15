package Windows.International;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalMenuItem extends JMenuItem implements InternationalComponent {
    private String localizationKey;

    // Constructors
    public InternationalMenuItem(String key) {
        this.localizationKey = key;
        this.updateText();
    }

    public InternationalMenuItem(String key, Icon icon) {
        super(icon);

        this.localizationKey = key;
        this.updateText();
    }

    public InternationalMenuItem(String key, int mnemonic) {
        this(key);

        this.setMnemonic(mnemonic);
    }

    public InternationalMenuItem(String key, Action actionlistener) {
        this(key);
        this.setAction(actionlistener);
        this.updateText();
    }

    // Getters & Setters

    public String getLocalizationKey() {
        if (localizationKey == null || localizationKey.equals(""))
            return "default";
        else
            return localizationKey;
    }

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    // Methods

    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(key == null || key.equals("") ? "default" : key));
    }

    public void updateText() {
        this.setText(localizationKey);
    }

    @Override
    protected void init(String text, Icon icon) {
        super.init(getLocalizationKey(), icon);
    }
}
