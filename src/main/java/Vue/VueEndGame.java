package Vue;

import javax.swing.*;

import Modele.Modele;

import java.awt.*;


public class VueEndGame extends JPanel {
    private Modele modele;
    private final int WIDTH;
    private final int HEIGHT;
    private final int BOX_SIZE = Constants.BOX_SIZE;

    public VueEndGame(Modele m){
        this.modele = m;

        this.WIDTH = this.modele.getGrille().getWidth();
        this.HEIGHT = this.modele.getGrille().getHeight();

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        // this.setPreferredSize(new Dimension(this.WIDTH*this.BOX_SIZE, this.HEIGHT*this.BOX_SIZE));
        this.add(new JLabel("GAME OVER...", SwingConstants.CENTER), BorderLayout.CENTER);
        this.setBackground(Color.RED);
    }
}
