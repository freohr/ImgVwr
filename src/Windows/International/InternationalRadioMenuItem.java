package Windows.International;

import Controler.Controller;
import Resources.Languages;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalRadioMenuItem extends JRadioButtonMenuItem implements InternationalComponent {
    private String localizationKey;
    private Languages language;

    public InternationalRadioMenuItem(String localizationKey, Languages option) {
        this.localizationKey = localizationKey;
        this.language = option;

        this.updateText();
    }

    public InternationalRadioMenuItem(Icon icon, String localizationKey, Languages option) {
        super(icon);
        this.localizationKey = localizationKey;
        this.language = option;

        this.updateText();
    }

    public InternationalRadioMenuItem(boolean selected, String localizationKey, Languages option) {
        this(localizationKey, option);

        this.setSelected(selected);
    }

    public InternationalRadioMenuItem(Icon icon, boolean selected, String localizationKey, Languages option) {
        super(icon, selected);
        this.localizationKey = localizationKey;
        this.language = option;

        this.updateText();
    }

    public InternationalRadioMenuItem(String localizationKey, Languages option, Action action) {
        this(localizationKey, option);
        this.setAction(action);
        this.updateText();
    }

    public InternationalRadioMenuItem(boolean selected, String localizationKey, Languages option, Action action) {
        this(selected, localizationKey, option);
        this.setAction(action);
        this.updateText();
    }

    public String getLocalizationKey() {
        return localizationKey;
    }

    public Languages getLanguage() {
        return language;
    }

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(key == null || key.equals("") ? "default" : key));
    }

    @Override
    public void updateText() {
        this.setText(localizationKey);
    }
}
