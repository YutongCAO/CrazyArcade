package projet.model;

import projet.util.ElementLoader;

import javax.swing.*;

/**
 * La classe de la bombe avant l'explosion, qui hérite de la classe de l'élément.
 */
public class Boom extends Box {

    private int situation;

    public Boom(int posX, int posY) {
        super(posX, posY, false);
        situation = 0;
    }

    public ImageIcon getIm() {
        return ElementLoader.imageMap.get("boom" + situation);
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public void run() {
        situation = ++situation % 4;
    }

}
