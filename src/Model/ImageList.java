package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

/**
 * Created by stephen on 11/02/2015.
 */
public class ImageList extends Observable {

    public ArrayList<Image> ImageList;
    public HashSet<String> tags;

    public ImageList() {
        ImageList = new ArrayList<>();
    }

    public ArrayList<Image> getImageList() {
        return ImageList;
    }

/*
    public void setImageList(ArrayList<Image> imageList) {
        ImageList = imageList;
    }*/

    public void convertImageList(HashMap<String, BufferedImage> images) {
        ImageList.clear();

        images.entrySet().stream().forEach(i -> ImageList.add(new Image(i.getValue(), i.getKey())));

        this.updateTags();

        this.setChanged();
        this.notifyObservers();
    }

    private void updateTags() {
        ImageList.stream().forEach(img -> img.tags.stream().forEach(tag -> tags.add(tag)));
    }

    public Image getImage(String title) {
        return ImageList.stream().filter(i -> i.title.equals(title)).findFirst().get();
    }
}
