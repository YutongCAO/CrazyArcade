package projet.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * La classe d'un outil pour charger les images à partir des fichiers.
 */
public class ImageUtils {

    /**
     * Le méthode pour charger une partie d'une image.
     *
     * @param input  Fichier à charger
     * @param type   Type de fichier (jpg, png)
     * @param x      Position X dans l'image
     * @param y      Position Y dans l'image
     * @param width  Largeur de la partie qu'on veut
     * @param height Hauteur de la partie qu'on veut
     * @return L'image au forme d'ImageIcon
     * @throws IOException l'exception de l'IO
     */
    public static ImageIcon cutImage(String input, String type, int x,
                                     int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        ImageIcon im;
        try {
            String imageType = (null == type || "".equals(type)) ? "jpg" : type;
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageType);
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(new FileInputStream(input));
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();

            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            im = new ImageIcon(bi);

        } finally {
            if (imageStream != null) {
                imageStream.close();
            }
        }
        return im;
    }
}  