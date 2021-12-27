package projet.view;

import projet.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Classe pour gérer le résultat du jeu
 */
public class FenetreFin extends JFrame {

    public FenetreFin(GamePanel gp) {
        Player[] player = gp.getPlayer();
        final JFrame jf = this;
        JPanel jp = new JPanel();
        JPanel jp2 = new JPanel();
        JLabel jl = new JLabel();
        if (player[0].isDead()) {
            if (player[1].isDead()) {
                jl.setText("No score, one more round?");
            } else {
                jl.setText("Player 2 win !");
            }
        } else {
            if (player[1].isDead()) {
                jl.setText("Player 1 win !");
            }
        }
        jp.add(jl);
        JButton jb = new JButton("Restart");
        jb.addActionListener(e -> {
            gp.getGrille().init();
            jf.setVisible(false);
        });
        JButton jb2 = new JButton("Exit");
        jb2.addActionListener(e -> System.exit(0));
        jp2.add(jb);
        jp2.add(jb2);

        this.setTitle("Result");
        this.setLayout(new BorderLayout());
        this.add(jp, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.SOUTH);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocation(136, 204);
        this.setSize(272, 136);
        this.setVisible(true);
    }
}
