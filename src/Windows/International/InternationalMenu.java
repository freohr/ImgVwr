package Windows.International;

import Controler.Controller;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by stephen on 14/03/2015.
 */
public class InternationalMenu extends JMenu implements InternationalComponent {
    private String localizationKey;

    public InternationalMenu(String key) {
        this.localizationKey = key;
        this.setText(key);
    }

    @Override
    protected void init(String text, Icon icon) {
        super.init(localizationKey, icon);
    }

    @Override
    public void setText(String key) {
        super.setText(ResourceBundle.getBundle("Resources.LabelBundle", Controller.getCurrentLocale()).getString(key));
    }

    @Override
    public void updateText() {
        this.setText(localizationKey);
    }
}
