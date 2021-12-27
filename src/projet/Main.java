package projet;

import projet.model.Grille;
import projet.util.ElementLoader;
import projet.util.MapLoader;
import projet.view.Fenetre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grille grille;
        String path = "data/path.txt";

        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            path = in.readLine();
            ElementLoader.loadImage();
            int[][] tab = MapLoader.mapLoader(path);
            grille = new Grille(tab);
            new Fenetre(grille);
        } catch (Exception e) {
            try {
                int choix = 0;
                while (choix != 1 && choix != 2 && choix != 3) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Choisissez le map (1, 2 ou 3) :");
                    choix = Integer.parseInt(scanner.nextLine());
                }
                path = "data/map" + choix + ".txt";
                ElementLoader.loadImage();
                int[][] tab = MapLoader.mapLoader(path);
                grille = new Grille(tab);
                new Fenetre(grille);
            } catch (Exception e2) {
                System.err.println("Error path");
            }
        }
    }
}
