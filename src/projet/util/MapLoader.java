package projet.util;

import projet.model.Grille;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe pour le chargement de la carte de la scène.
 */
public class MapLoader {

    public static int playerX;
    public static int playerY;
    public static int floor;

    /**
     * Chargeur de la carte avec le chemin du fichier indiqué
     *
     * @param path le chemin du fichier
     * @return le tableau de la carte avec les types des boîtes
     * @throws IOException l'exception d'IO
     */
    public static int[][] mapLoader(String path) throws IOException {
        int[][] tab;
        BufferedReader in = new BufferedReader(new FileReader(path));
        int width = Integer.parseInt(in.readLine());
        int height = Integer.parseInt(in.readLine());
        playerX = Integer.parseInt(in.readLine());
        playerY = Integer.parseInt(in.readLine());
        floor = Integer.parseInt(in.readLine());
        Grille.GRILLE_WIDTH = width;
        Grille.GRILLE_HEIGHT = height;
        tab = new int[height][width];
        for (int i = 0; i < height; i++) {
            String buf = in.readLine();
            String[] values = buf.split("\t");
            for (int j = 0; j < width; j++) {
                tab[i][j] = Integer.parseInt(values[j]);
            }
        }
        in.close();

        return tab;
    }

}
