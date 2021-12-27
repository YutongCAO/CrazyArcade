package projet.model;

import javax.swing.*;

/**
 * Classe abstraite représentant tous les objets de la scène.
 */
public abstract class Element {

    /* Position de l'element **/
    protected int posX, posY;
    /* L'image de l'element **/
    protected ImageIcon im;

    public Element(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public abstract ImageIcon getIm();

    public void setIm(ImageIcon im) {
        this.im = im;
    }

}
