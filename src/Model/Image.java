package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by stephen on 11/02/2015.
 */
public class Image {
    public BufferedImage file;
    public String description;
    public String title;
    public HashSet<String> tags;

    public Image(BufferedImage image) {
        this.file = image;
        this.description = "";
        this.tags = new HashSet<>();
        this.title = "";
    }

    public Image(BufferedImage file, String title) {
        this(file);
        this.title = title;
    }

    // Getters
    public BufferedImage getImage() {
        return file;
    }

    public String getDescription() {
        return description;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    // Setters

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public void removeTag(String tag){
        this.tags.remove(tag);
    }
}
