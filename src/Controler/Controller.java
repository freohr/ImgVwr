package Controler;

import Model.Image;
import Model.ImageList;
import com.sun.javafx.sg.prism.NGShape;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.gif.GifImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.png.PngImageParser;
import org.apache.commons.io.FilenameUtils;
import sun.awt.image.GifImageDecoder;

import javax.swing.plaf.multi.MultiFileChooserUI;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
