package Vue;

import Modele.*;

import java.awt.*;
import javax.swing.*;

public class VueCase extends JPanel implements Observer {
    private final int BOX_SIZE = 60;
    
    private final Color COLOR_MER = new Color(47, 128, 124);
    private final Color COLOR_SEC = new Color(48, 97, 14);
    private final Color COLOR_INONDE = new Color(101, 147, 99);

    private Modele modele;
    private Case c;
    private JPanel panel = new JPanel();
    private JLabel icon_joueur = new JLabel(Utils.scaleImage(Constants.ICON_JOUEUR, this.BOX_SIZE/2, this.BOX_SIZE/2));

    public VueCase(Modele m, Case c) {
        this.c = c;

        this.modele = m;
        this.modele.addObserver(this);
        
        this.setPreferredSize(new Dimension(this.BOX_SIZE, this.BOX_SIZE));
        this.paintJoueurs();
        
        this.panel.setOpaque(false);
        this.panel.setPreferredSize(new Dimension(this.BOX_SIZE, this.BOX_SIZE));
        this.add(this.panel);
    }

    public void update() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        paintSol(g);
        paintJoueurs();
    }

    private void paintJoueurs(){
        this.panel.remove(this.icon_joueur);

        if (this.modele.getJoueurActuel().getCoord() == this.c.coord) {
            this.panel.add(this.icon_joueur);
        }
    }

    private void paintSol(Graphics g) {
        if (this.c.terrain == Terrain.MER)
            g.setColor(this.COLOR_MER);

        else if(this.c.helipad)
            g.setColor(Color.GRAY);
        
        else if(this.c.aObjet(Artefact.class))
            g.setColor(Color.YELLOW);
        
        else if(this.c.aObjet(Clef.class))
            g.setColor(Color.PINK);

        else {
            switch (this.c.getEtat()) {
                case SECHE:
                    g.setColor(this.COLOR_SEC);
                    break;

                case INONDEE:
                    g.setColor(this.COLOR_INONDE);
                    break;

                case SUBMERGEE:
                    g.setColor(this.COLOR_MER);
                    break;

                default:
                    g.setColor(Color.WHITE);
                    break;
            }
        }

        g.fillRect(0, 0, this.BOX_SIZE, this.BOX_SIZE);
    }

    private void fillCircle(Graphics g, int center_x, int center_y, int radius){
        g.fillOval(center_x-radius, center_y-radius, 2*radius, 2*radius);
    }
}
