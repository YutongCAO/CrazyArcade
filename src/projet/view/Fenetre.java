package projet.view;

import projet.controller.ClavierListener;
import projet.model.Grille;
import projet.util.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Classe de fenêtre, qui contient un panel.
 */
public class Fenetre extends JFrame {

    public Fenetre(Grille grille) {
        MenuBar menuBar = new MenuBar();
        Menu menuMap = new Menu("Map");
        MenuItem map1 = new MenuItem("Map 1");
        MenuItem map2 = new MenuItem("Map 2");
        MenuItem map3 = new MenuItem("Map 3");
        Fenetre f = this;

        map1.addActionListener(e -> {
            try {
                int[][] tab = MapLoader.mapLoader("data/map1.txt");
                Grille grille1 = new Grille(tab);
                f.setVisible(false);
                new Fenetre(grille1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        map2.addActionListener(e -> {
            try {
                int[][] tab = MapLoader.mapLoader("data/map2.txt");
                Grille grille1 = new Grille(tab);
                f.setVisible(false);
                new Fenetre(grille1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        map3.addActionListener(e -> {
            try {
                int[][] tab = MapLoader.mapLoader("data/map3.txt");
                Grille grille1 = new Grille(tab);
                f.setVisible(false);
                new Fenetre(grille1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        menuMap.add(map1);
        menuMap.add(map2);
        menuMap.add(map3);
        menuBar.add(menuMap);
        this.setMenuBar(menuBar);

        GamePanel gp = new GamePanel(grille);
        this.setTitle("CrazyArcade");
        this.addKeyListener(new ClavierListener(gp));
        this.setLayout(new BorderLayout());
        this.add(gp, BorderLayout.CENTER);
        this.setBackground(Color.GRAY);
        this.setSize(558, 560);
        this.setLocation(500, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
