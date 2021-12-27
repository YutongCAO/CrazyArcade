package projet.model;

import javax.swing.*;

/**
 * La classe définit les briques de la scène, qui hérite de la classe de la brique.
 */
public class Box extends Element {

    public static final int BOX_SIZE = 32;
    private boolean eliminable;
    private boolean passable;

    public Box(int positionX, int positionY, boolean passable) {
        super(positionX, positionY);
        this.passable = passable;
    }

    public Box(int positionX, int positionY, boolean passable, boolean eliminable, ImageIcon im) {
        this(positionX, positionY, passable);
        this.eliminable = eliminable;
        this.setIm(im);
    }

    public Box(int positionX, int positionY, ImageIcon im) {
        super(positionX, positionY);
        this.passable = false;
        this.eliminable = false;
        this.im = im;
    }

    public ImageIcon getIm() {
        return im;
    }

    public boolean isEliminable() {
        return eliminable;
    }

    public void setEliminable(boolean eliminable) {
        this.eliminable = eliminable;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }


}
