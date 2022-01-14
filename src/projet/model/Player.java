package projet.model;

import projet.util.ElementLoader;

import javax.swing.*;

/**
 * La classe du joueur, qui hérite de la classe de l'élément.
 */
public class Player extends Element {

    private int pointVie;
    private int direction;
    private int numPlayer;
    private boolean visible;

    public Player(int posX, int posY, int numPlayer, int pointVie) {
        super(posX, posY);
        this.direction = 0;
        this.numPlayer = numPlayer;
        this.im = ElementLoader.imageMap.get("player" + numPlayer + direction);
        this.pointVie = pointVie;
        this.visible = true;
    }

    public void killed() {
        pointVie--;
    }

    public boolean isDead() {
        return pointVie <= 0;
    }

    public int getPointVie() { return pointVie; }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public ImageIcon getIm() {
        im = ElementLoader.imageMap.get("player" + numPlayer + direction);
        return im;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void showPosition() {
        System.out.println("Position : (" + posX + "," + posY + ")");
    }

}
