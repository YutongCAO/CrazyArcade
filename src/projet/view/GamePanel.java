package projet.view;

import projet.model.Box;
import projet.model.Grille;
import projet.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * La classe du panneau principal du jeu, qui hérite de JPanel, est utilisée pour dessiner les éléments de la scène.
 */
public class GamePanel extends JPanel {

    public final Grille grille;
    public final Player[] player;

    public GamePanel(Grille grille) {
        this.grille = grille;
        player = grille.getPlayer();
    }

    public Grille getGrille() {
        return grille;
    }

    public Player[] getPlayer() {
        return player;
    }

    //先画炸弹附近地板，再画可通过地板，再画人以上的box，再画第一个人，再画第一个人到第二个人之间的box，再画第二个人，再画第二个人一下的box
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBoomFloor(g);
        drawFloor(g);
        Player p1, p2;

        //判断player前后顺序
        if (player[0].getPosY() / Box.BOX_SIZE * Grille.GRILLE_WIDTH + player[0].getPosX() / Box.BOX_SIZE < player[1].getPosY() / Box.BOX_SIZE * Grille.GRILLE_WIDTH + player[1].getPosX() / Box.BOX_SIZE) {
            p1 = player[0];
            p2 = player[1];
        } else {
            p1 = player[1];
            p2 = player[0];
        }

        drawBox(g, 0, 1 + p1.getPosY() / Box.BOX_SIZE);
        if (p1.isVisible())
            drawPlayer(g, p1);
        drawBox(g, 1 + p1.getPosY() / Box.BOX_SIZE, 1 + p2.getPosY() / Box.BOX_SIZE);
        if (p2.isVisible())
            drawPlayer(g, p2);
        drawBox(g, 1 + p2.getPosY() / Box.BOX_SIZE, Grille.GRILLE_HEIGHT);

    }

    //除地板之外其他的box
    private void drawBox(Graphics g, int debut, int fin) {
        for (int i = debut; i < fin; i++) {
            for (int j = 0; j < Grille.GRILLE_WIDTH; j++) {
                if (!grille.getGrilleBox().get(new Point(j * Box.BOX_SIZE, i * Box.BOX_SIZE)).isPassable())
                    drawLigne(g, i, j);
            }
        }
    }

    //只画可通过的，让其成为地板
    private void drawFloor(Graphics g) {
        for (int i = 0; i < Grille.GRILLE_HEIGHT; i++) {
            for (int j = 0; j < Grille.GRILLE_WIDTH; j++) {
                if (grille.getGrilleBox().get(new Point(j * Box.BOX_SIZE, i * Box.BOX_SIZE)).isPassable())
                    drawLigne(g, i, j);
            }
        }
    }

    //画出指定玩家
    private void drawPlayer(Graphics g, Player player) {
        ImageIcon im = player.getIm();
        int height = im.getIconHeight();
        int width = im.getIconWidth();
        int posImgPlayerX = player.getPosX() - 34;
        int posImgPlayerY = player.getPosY() - 36 - 8;
        g.drawImage(im.getImage(), posImgPlayerX, posImgPlayerY, posImgPlayerX + width, posImgPlayerY + height, 0, 0, width, height, null);
    }

    //在炸弹列表中找出并画炸弹
    private void drawBoomFloor(Graphics g) {
        for (Point point : grille.getGrilleBoom().keySet()) {
            Box boom = grille.getGrilleBoom().get(point);
            ImageIcon im = boom.getIm();
            int height = im.getIconHeight();
            int width = im.getIconWidth();
            g.drawImage(im.getImage(), boom.getPosX(), boom.getPosY() + Box.BOX_SIZE, boom.getPosX() + Box.BOX_SIZE, boom.getPosY() + 2 * Box.BOX_SIZE, 0, 0, width, height, null);
        }
    }

    //在i行j列绘制图片到g
    private void drawLigne(Graphics g, int i, int j) {
        if (grille.getGrilleBox().get(new Point(j * Box.BOX_SIZE, i * Box.BOX_SIZE)).getIm() != null) {
            ImageIcon im = grille.getGrilleBox().get(new Point(j * Box.BOX_SIZE, i * Box.BOX_SIZE)).getIm();
            int height = im.getIconHeight();
            int width = im.getIconWidth();
            g.drawImage(im.getImage(), (j + 1) * Box.BOX_SIZE - width, (i + 2) * Box.BOX_SIZE - height, (j + 1) * Box.BOX_SIZE, (i + 2) * Box.BOX_SIZE, 0, 0, width, height, null);
        }
    }

}
