package projet.controller;

import projet.model.Box;
import projet.model.Player;
import projet.view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe du controleur pour déterminer si le joueur est tué.
 */
public class ControllerKill {

    private final GamePanel gp;
    private final Point point;
    private final Player[] player;

    public ControllerKill(Point point, GamePanel gp) {
        this.point = point;
        this.gp = gp;
        this.player = gp.getPlayer();
    }

    //判断当前point点上是否有人并且输出人的列表
    public List<Player> playerKilled() {
        List<Player> list = new ArrayList<>();
        for (Player value : player) {
            int newPosX = value.getPosX();
            int newPosY = value.getPosY();
            int posXLeft = Box.BOX_SIZE * (newPosX / Box.BOX_SIZE);
            int posYUp = Box.BOX_SIZE * (newPosY / Box.BOX_SIZE);
            if (point.equals(new Point(posXLeft, posYUp))) {
                list.add(value);
            }
        }
        return list;
    }

}
