package controllers;

import models.Image;

/**
 * Created by Jhon on 11/7/2017.
 */
public class ImageDao extends DbManager<Image,Integer> {

    private ImageDao() {
        super(Image.class);
    }

    private static ImageDao instance;

    public static ImageDao getInstance() {
        if (instance == null) {
            instance = new ImageDao();
        }
        return instance;
    }
}
