package projet.util;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe pour charger les images des éléments et les mettre dans la Map.
 */
public class ElementLoader {

    public static Map<String, ImageIcon> imageMap = new HashMap<>();

    /**
     * Chargeur d'image avec le chemin
     */
    public static void loadImage() {
        try {
            for (int i = 0; i < 10; i++) {
                ImageIcon icon = ImageUtils.cutImage("data/box.png", "png", (i % 4) * 32, (i / 4) * 32, 32, 32);
                imageMap.put("box" + i, icon);
            }
            ImageIcon icon = ImageUtils.cutImage("data/box2.png", "png", 32, 0, 32, 64);
            imageMap.put("box11", icon);
            icon = ImageUtils.cutImage("data/box2.png", "png", 64, 0, 32, 64);
            imageMap.put("box12", icon);
            icon = ImageUtils.cutImage("data/box2.png", "png", 32, 64, 32, 48);
            imageMap.put("box13", icon);
            icon = ImageUtils.cutImage("data/box3.png", "png", 0, 0, 32, 32);
            imageMap.put("box14", icon);

            for (int i = 0; i < 16; i++) {
                icon = ImageUtils.cutImage("data/player1.png", "png", (i % 4) * 100, (i / 4) * 100, 100, 100);
                imageMap.put("player1" + i, icon);
            }

            for (int i = 0; i < 16; i++) {
                icon = ImageUtils.cutImage("data/player2.png", "png", (i % 4) * 100, (i / 4) * 100, 100, 100);
                imageMap.put("player2" + i, icon);
            }

            for (int i = 0; i < 4; i++) {
                icon = ImageUtils.cutImage("data/boom1.png", "png", i * 32, 0, 32, 48);
                imageMap.put("boom" + i, icon);
            }

            for (int i = 0; i < 16; i++) {
                icon = ImageUtils.cutImage("data/boom2.png", "png", (i % 5) * 192, (i / 5) * 192, 192, 192);
                imageMap.put("boomExplode" + i, icon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
