package projet.model;

import projet.controller.ControllerKill;
import projet.util.ElementLoader;
import projet.util.MapLoader;
import projet.view.FenetreFin;
import projet.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.*;

/**
 * La classe avec les effets de l'explosion de la bombe, qui hérite de la classe de la brique.
 */
public class BoomExplode extends Box {

    final Map<Point, Box> grilleBox;
    final Map<Point, Box> grilleBoom;
    private final GamePanel gp;
    private final int force;
    private ImageIcon[] images;
    private Point[] points;

    public BoomExplode(Point p, int force, GamePanel gp) {
        super(p.x, p.y, false);
        this.force = force;
        this.gp = gp;
        this.grilleBox = gp.getGrille().getGrilleBox();
        this.grilleBoom = gp.getGrille().getGrilleBoom();
        init();
    }

    public BoomExplode(int positionX, int positionY, int force, GamePanel gp) {
        super(positionX, positionY, false);
        this.force = force;
        this.gp = gp;
        this.grilleBox = gp.getGrille().getGrilleBox();
        this.grilleBoom = gp.getGrille().getGrilleBoom();
        init();
    }

    public void init() {
        images = new ImageIcon[16];
        points = new Point[16];
        images[0] = ElementLoader.imageMap.get("boomExplode5");
        images[1] = ElementLoader.imageMap.get("boomExplode2");
        images[2] = ElementLoader.imageMap.get("boomExplode0");
        images[3] = ElementLoader.imageMap.get("boomExplode4");
        images[4] = ElementLoader.imageMap.get("boomExplode3");
        images[5] = ElementLoader.imageMap.get("boomExplode8");
        images[6] = ElementLoader.imageMap.get("boomExplode7");
        images[7] = ElementLoader.imageMap.get("boomExplode1");
        images[8] = ElementLoader.imageMap.get("boomExplode6");

        points[0] = new Point(this.posX, this.posY);
        points[1] = new Point(this.posX - 2 * Box.BOX_SIZE, this.posY);
        points[2] = new Point(this.posX + 2 * Box.BOX_SIZE, this.posY);
        points[3] = new Point(this.posX, this.posY - 2 * Box.BOX_SIZE);
        points[4] = new Point(this.posX, this.posY + 2 * Box.BOX_SIZE);
        points[5] = new Point(this.posX - Box.BOX_SIZE, this.posY);
        points[6] = new Point(this.posX + Box.BOX_SIZE, this.posY);
        points[7] = new Point(this.posX, this.posY - Box.BOX_SIZE);
        points[8] = new Point(this.posX, this.posY + Box.BOX_SIZE);

    }

    //炸弹爆炸过程
    public void explode() {
        if (force == 1) {
            Box bombe = new Box(posX, posY, images[0]);
            bombe.setPassable(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    grilleBox.put(points[0], bombe);
                    gp.repaint();
                }
            }, 0);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Player> playerKilled = new ArrayList<>();
                    for (int i = 0; i < 9; i++) {
                        playerKilled.addAll(new ControllerKill(points[i], gp).playerKilled());
                    }
                    if (playerKilled.size() != 0) {
                        for (var p : playerKilled) {
                            p.killed();
                            TimerTask tt = new TimerTask() {
                                @Override
                                public void run() {
                                    p.setVisible(!p.isVisible());
                                    gp.repaint();
                                }
                            };
                            timer.schedule(tt, 0, 300);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    tt.cancel();
                                    p.setVisible(true);
                                    gp.repaint();
                                }
                            }, 1800);
                        }
                        if (gp.getGrille().isFin()) {
                            new FenetreFin(gp);
                        }
                    }
                    for (int i = 1; i < 9; i++) {
                        if (points[i] != null) {
                            changePic(i, grilleBox, grilleBoom, false);
                        }
                    }
                    gp.repaint();
                }
            }, 100);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (int i = 0; i < 9; i++) {
                        if (points[i] != null) {
                            changePic(i, grilleBoom, grilleBox, true);
                        }
                    }
                    gp.repaint();
                }
            }, 1000);

            gp.repaint();
        }
    }

    //通过判断炸弹附近格子是否可以被炸掉来选择不同的炸弹炸开图片。可通行，不可通行可被炸，其他。
    private void changePic(int i, Map<Point, Box> source, Map<Point, Box> dest, boolean remove) {
        if (i == 0) {
            if (source.containsKey(points[i])) {
                boxPassable(i, source, dest, remove);
            }
        } else if (i < 5) {
            if (source.containsKey(points[i]))
                if (source.get(points[i + 4]).isPassable()) {
                    if (source.get(points[i]).isEliminable())
                        boxEliminable(i, i, source, dest, remove);
                    else if (source.get(points[i]).isPassable()) {
                        boxPassable(i, source, dest, remove);
                    }
                }
        } else if (source.containsKey(points[i]))
            if (source.get(points[i]).isEliminable()) {
                boxEliminable(i, i - 4, source, dest, remove);
            } else if (source.get(points[i]).isPassable()) {
                dest.put(points[i], source.get(points[i]));
                if (remove) {
                    source.remove(points[i]);
                } else if (source.containsKey(points[i - 4]))
                    if (source.get(points[i - 4]).isEliminable() || source.get(points[i - 4]).isPassable())
                        source.put(points[i], new Box(points[i].x, points[i].y, source.get(points[i]).isPassable(), source.get(points[i]).isEliminable(), images[i]));
                    else
                        source.put(points[i], new Box(points[i].x, points[i].y, source.get(points[i]).isPassable(), source.get(points[i]).isEliminable(), images[i - 4]));
            }
    }

    //把方块从source搬到dest，true拿回来，false拿走放炸弹图
    private void boxEliminable(int i, int j, Map<Point, Box> source, Map<Point, Box> dest, boolean remove) {
        if (remove) {
            dest.put(points[i], new Box(points[i].x, points[i].y, true, false, ElementLoader.imageMap.get("box" + MapLoader.floor)));
            source.remove(points[i]);
        } else {
            dest.put(points[i], source.get(points[i]));
            source.put(points[i], new Box(points[i].x, points[i].y, source.get(points[i]).isPassable(), source.get(points[i]).isEliminable(), images[j]));
        }
    }

    //把地板从source搬到dest，true地板拿回来，false拿走地板放炸弹图
    private void boxPassable(int i, Map<Point, Box> source, Map<Point, Box> dest, boolean remove) {
        dest.put(points[i], source.get(points[i]));
        if (remove) {
            source.remove(points[i]);
        } else {
            source.put(points[i], new Box(points[i].x, points[i].y, source.get(points[i]).isPassable(), source.get(points[i]).isEliminable(), images[i]));
        }
    }
}
