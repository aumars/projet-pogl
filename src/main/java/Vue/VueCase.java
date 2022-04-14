package Vue;

import Modele.*;

import java.awt.*;
import javax.swing.*;

public class VueCase extends JPanel implements Observer {
    private final int BOX_SIZE = Constants.BOX_SIZE;

    private final Color COLOR_MER = new Color(47, 128, 124);
    private final Color COLOR_SEC = new Color(48, 97, 14);
    private final Color COLOR_INONDE = new Color(101, 147, 99);

    private Modele modele;
    private Case c;
    private JPanel panel = new JPanel();
    private JLabel icon_joueur = new JLabel(
            Utils.scaleImage(Constants.ICON_JOUEUR, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
    private JLabel icon_objet = new JLabel();

    public VueCase(Modele m, Case c) {
        this.c = c;

        this.modele = m;
        this.modele.addObserver(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        paintObjet();
    }

    private void paintJoueurs() {
        this.panel.remove(this.icon_joueur);

        if (!this.modele.getJoueurActuel().estVivant()) {
            this.icon_joueur.setIcon(Utils.scaleImage(Constants.ICON_TOMB, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
            this.panel.add(this.icon_joueur);
        } else if (this.modele.getJoueurActuel().getCoord() == this.c.coord) {
            this.panel.add(this.icon_joueur);
        }
    }

    private void paintObjet() {
        this.panel.remove(this.icon_objet);
        // this.icon_objet.setVisible(this.c.getObjetVisibilite());

        if (this.c.aObjet(Clef.class)) {
            this.icon_objet
                    .setIcon(Utils.scaleImage(Constants.ICON_CLEF, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
            
            this.panel.add(this.icon_objet);
        }

        else if (this.c.aObjet(Artefact.class)) {
            switch (this.c.getObjet().element) {
                case EAU:
                    this.icon_objet
                            .setIcon(Utils.scaleImage(Constants.ICON_EAU, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
                    break;
                case AIR:
                    this.icon_objet
                            .setIcon(Utils.scaleImage(Constants.ICON_AIR, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
                    break;
                case TERRE:
                    this.icon_objet
                            .setIcon(Utils.scaleImage(Constants.ICON_TERRE, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
                    break;
                case FEU:
                    this.icon_objet
                            .setIcon(Utils.scaleImage(Constants.ICON_FEU, this.BOX_SIZE / 2, this.BOX_SIZE / 2));
                    break;

                default:
                    break;
            }

            this.panel.add(this.icon_objet);
        }
    }

    private void paintSol(Graphics g) {
        if (this.c.terrain == Terrain.MER)
            g.setColor(this.COLOR_MER);

        else if (this.c.helipad)
            g.setColor(Color.GRAY);

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
}
