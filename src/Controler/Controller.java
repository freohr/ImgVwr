package Controler;

import Model.Image;
import Model.ImageList;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by stephen on 11/02/2015.
 */
public class Controller {
    public ImageList Model;

    public Controller(ImageList model) {
        Model = model;
    }

    public void importImages(File[] fileArrays) {

        HashMap<String, BufferedImage> images = new HashMap<>();

        for(File img : fileArrays){
            try {

                images.put(img.getName(), Imaging.getBufferedImage(img));
            } catch (ImageReadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Model.convertImageList(images);
    }

    public ArrayList<Image> getImages(){
        return Model.getImageList();
    }

}
