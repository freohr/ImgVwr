package Model;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONString;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by stephen on 11/02/2015.
 */
public class Image implements JSONString {

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

        String descFileName = FilenameUtils.removeExtension(title) + ".txt";

        // TODO : Read from JSON File
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

        String descFileName = FilenameUtils.removeExtension(title) + ".txt";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    @Override
    public String toJSONString() {
        StringBuilder builder = new StringBuilder();

        // Open Object
        builder.append("{");

        // Premier membre : description
        builder.append("\"description\":");
        builder.append("\"" + description + "\"");

        builder.append(",");

        // Deuxième membre : tags
        builder.append("\"tags\":");

        // On crée le tableau de tags
        builder.append("[");

        for (String tag : tags) {
            builder.append("\"" + tag + "\"");
        }

        builder.append("]");


        builder.append("}");

        return builder.toString();
    }

    public void WriteToFile() {
        String jsonContent = toJSONString();

        String fileName = FilenameUtils.removeExtension(title) + ".json";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write(jsonContent);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
