package projet.controller;

import projet.model.*;
import projet.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * La classe d'écouteurs de clavier est utilisée pour le contrôle du joueur.
 */
public class ClavierListener implements KeyListener {

    public final static int STEP_SIZE = 16;
    private final GamePanel gp;
    private final Player[] player;
    private final Map<Point, Box> grilleBoom;
    private final Map<Point, Box> grilleBox;
    private final boolean[] left, right, up, down;
    private final int[] direction, newPosX, newPosY, nombreBoom;

    public ClavierListener(GamePanel gp) {
        this.gp = gp;
        this.player = gp.getPlayer();
        grilleBoom = gp.getGrille().getGrilleBoom();
        grilleBox = gp.getGrille().getGrilleBox();
        direction = new int[player.length];
        newPosX = new int[player.length];
        newPosY = new int[player.length];
        nombreBoom = new int[player.length];
        left = new boolean[player.length];
        right = new boolean[player.length];
        up = new boolean[player.length];
        down = new boolean[player.length];

        for (int i = 0; i < player.length; i++) {
            nombreBoom[i] = 0;
            left[i] = false;
            right[i] = false;
            up[i] = false;
            down[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < gp.getPlayer().length; i++) {
            readData(i);
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                bombe(0);
                break;
            case KeyEvent.VK_Q:
                threadLeft(0);
                break;
            case KeyEvent.VK_D:
                threadRight(0);
                break;
            case KeyEvent.VK_Z:
                threadUp(0);
                break;
            case KeyEvent.VK_S:
                threadDown(0);
                break;
            case KeyEvent.VK_ENTER:
                bombe(1);
                break;
            case KeyEvent.VK_LEFT:
                threadLeft(1);
                break;
            case KeyEvent.VK_RIGHT:
                threadRight(1);
                break;
            case KeyEvent.VK_UP:
                threadUp(1);
                break;
            case KeyEvent.VK_DOWN:
                threadDown(1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                left[0] = false;
                break;
            case KeyEvent.VK_D:
                right[0] = false;
                break;
            case KeyEvent.VK_Z:
                up[0] = false;
                break;
            case KeyEvent.VK_S:
                down[0] = false;
                break;
            case KeyEvent.VK_LEFT:
                left[1] = false;
                break;
            case KeyEvent.VK_RIGHT:
                right[1] = false;
                break;
            case KeyEvent.VK_UP:
                up[1] = false;
                break;
            case KeyEvent.VK_DOWN:
                down[1] = false;
                break;
        }
    }

    private void readData(int i) {
        direction[i] = player[i].getDirection();
        newPosX[i] = player[i].getPosX();
        newPosY[i] = player[i].getPosY();
    }

    private void threadLeft(int i) {
        if (!left[i]) {
            left[i] = true;
            right[i] = false;
            up[i] = false;
            down[i] = false;
            new Thread(() -> {
                while (left[i]) {
                    moveLeft(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void threadRight(int i) {
        if (!right[i]) {
            left[i] = false;
            right[i] = true;
            up[i] = false;
            down[i] = false;
            new Thread(() -> {
                while (right[i]) {
                    moveRight(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void threadUp(int i) {
        if (!up[i]) {
            left[i] = false;
            right[i] = false;
            up[i] = true;
            down[i] = false;
            new Thread(() -> {
                while (up[i]) {
                    moveUp(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void threadDown(int i) {
        if (!down[i]) {
            left[i] = false;
            right[i] = false;
            up[i] = false;
            down[i] = true;
            new Thread(() -> {
                while (down[i]) {
                    moveDown(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void moveLeft(int i) {
        readData(i);
        newPosX[i] -= STEP_SIZE;
        if (isPassable(1, newPosX[i], newPosY[i])) {
            player[i].setPosX(newPosX[i]);
        }
        if (direction[i] >= 4 && direction[i] < 8) {
            player[i].setDirection((direction[i] + 1) % 4 + 4);
        } else {
            player[i].setDirection(4);
        }
        gp.repaint();
    }

    private void moveRight(int i) {
        readData(i);
        newPosX[i] += STEP_SIZE;
        if (isPassable(2, newPosX[i], newPosY[i])) {
            player[i].setPosX(newPosX[i]);
        }
        if (direction[i] >= 8 && direction[i] < 12) {
            player[i].setDirection((direction[i] + 1) % 4 + 8);
        } else {
            player[i].setDirection(8);
        }
        gp.repaint();
    }

    private void moveUp(int i) {
        readData(i);
        newPosY[i] -= STEP_SIZE;
        if (isPassable(3, newPosX[i], newPosY[i])) {
            player[i].setPosY(newPosY[i]);
        }
        if (direction[i] >= 12 && direction[i] < 16) {
            player[i].setDirection((direction[i] + 1) % 4 + 12);
        } else {
            player[i].setDirection(12);
        }
        gp.repaint();
    }

    private void moveDown(int i) {
        readData(i);
        newPosY[i] += STEP_SIZE;
        if (isPassable(4, newPosX[i], newPosY[i])) {
            player[i].setPosY(newPosY[i]);
        }
        if (direction[i] >= 0 && direction[i] < 4) {
            player[i].setDirection((direction[i] + 1) % 4);
        } else {
            player[i].setDirection(0);
        }
        gp.repaint();
    }

    //预测走路之后位置附近的两个格子是否能通过
    private boolean isPassable(int direction, int newPosX, int newPosY) {
        if (newPosY > (Grille.GRILLE_HEIGHT - 2) * Box.BOX_SIZE)
            return false;
        Point point;
        Point point2;
        int posXLeft = Box.BOX_SIZE * (newPosX / Box.BOX_SIZE);
        int poxXRight = Box.BOX_SIZE * ((newPosX + Box.BOX_SIZE - STEP_SIZE) / Box.BOX_SIZE);
        int posYDown = Box.BOX_SIZE * ((newPosY + Box.BOX_SIZE - STEP_SIZE) / Box.BOX_SIZE);
        int posYUp = Box.BOX_SIZE * (newPosY / Box.BOX_SIZE);
        switch (direction) {
            case 1:
                point = new Point(posXLeft, posYUp);
                point2 = new Point(posXLeft, posYDown);
                break;
            case 2:
                point = new Point(poxXRight, posYUp);
                point2 = new Point(poxXRight, posYDown);
                break;
            case 3:
                point = new Point(posXLeft, posYUp);
                point2 = new Point(poxXRight, posYUp);
                break;
            case 4:
                point = new Point(posXLeft, posYDown);
                point2 = new Point(poxXRight, posYDown);
                break;
            default:
                return false;
        }
        //判断走路前方是否有box，并且看是不是地板
        if (grilleBox.containsKey(point) && grilleBox.containsKey(point2))
            return grilleBox.get(point).isPassable() && grilleBox.get(point2).isPassable();
        return false;
    }

    //放炸弹，替换炸弹和地板图片，炸弹图片变化循环，炸弹炸开
    private synchronized void bombe(int i) {
        if (nombreBoom[i] < 1) {
            nombreBoom[i]++;
            Boom boom = new Boom(Box.BOX_SIZE * (newPosX[i] / Box.BOX_SIZE), Box.BOX_SIZE * (newPosY[i] / Box.BOX_SIZE));
            Point point = new Point(boom.getPosX(), boom.getPosY());

            grilleBoom.put(point, grilleBox.get(point));
            grilleBox.put(point, boom);

            //延时执行。从0开始每800毫秒循环一次，总共循环两点多次。第一个循环终止，在第2000毫秒执行。在3001毫秒执行。
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    boom.run();
                    gp.repaint();
                }
            }, 0, 800);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    BoomExplode be = new BoomExplode(point, 1, gp);
                    be.explode();
                }
            }, 2000);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nombreBoom[i]--;
                }
            }, 3001);
        }
    }

}
