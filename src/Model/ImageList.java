package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by stephen on 11/02/2015.
 */
public class ImageList extends Observable{

    public ArrayList<Image> ImageList;

    public ImageList() {
        ImageList = new ArrayList<>();
    }
}
