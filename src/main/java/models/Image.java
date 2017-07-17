package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Jhon on 11/7/2017.
 */

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Lob
    private byte[] image;

    public Image() {
    }

    public Image(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImgBase64() {
        return Base64.getEncoder().encodeToString(image);
    }


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
