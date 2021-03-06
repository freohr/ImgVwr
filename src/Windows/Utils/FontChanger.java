package Windows.Utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by stephen on 15/03/2015.
 */
public class FontChanger {

    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
}
