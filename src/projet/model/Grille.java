package projet.model;

import projet.util.ElementLoader;
import projet.util.MapLoader;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe qui quadrille la scène et enregistre les éléments individuels.
 */
public class Grille {

    //读取地图文件定义，全局变量
    public static int GRILLE_HEIGHT;
    public static int GRILLE_WIDTH;

    //grilleBoom存放的是放下炸弹之后，炸弹周围的砖块和地面。而炸弹被放进了grilleBox里
    private final Map<Point, Box> grilleBox;
    private final Map<Point, Box> grilleBoom;
    private final Player[] player;
    private final int[][] tab;

    public Grille(int[][] tab) {
        this.grilleBox = new HashMap<>();
        this.grilleBoom = new HashMap<>();
        this.tab = tab;
        this.player = new Player[2];
        init();
    }

    public void init() {
        for (int i = 0; i < GRILLE_HEIGHT; i++) {
            for (int j = 0; j < GRILLE_WIDTH; j++) {
                Box box = new Box(j * Box.BOX_SIZE, i * Box.BOX_SIZE, ElementLoader.imageMap.get("box" + tab[i][j]));
                box.setPassable(tab[i][j] == 2 || tab[i][j] == 8);
                box.setEliminable(tab[i][j] != 2 && tab[i][j] != 7 && tab[i][j] != 8 && tab[i][j] < 11);
                this.grilleBox.put(new Point(box.getPosX(), box.getPosY()), box);
            }
        }
        this.player[0] = new Player(32 * MapLoader.playerX, 32 * MapLoader.playerY, 1, 3);
        this.player[1] = new Player(32 * (16 - MapLoader.playerX), 32 * (14 - MapLoader.playerY), 2, 3);
    }

    public boolean isFin() {
        return this.player[0].isDead() || this.player[1].isDead();
    }

    public Map<Point, Box> getGrilleBox() {
        return grilleBox;
    }

    public Map<Point, Box> getGrilleBoom() {
        return grilleBoom;
    }

    public Player[] getPlayer() {
        return player;
    }

    public int[][] getTab() {
        return tab;
    }


}
