package Vue;

import Modele.*;

import java.awt.*;
import javax.swing.*;

public class VueCase extends JPanel implements Observer {
    private final int BOX_SIZE = Constants.BOX_SIZE;

    private final Color COLOR_MER = new Color(47, 128, 124);
    private final Color COLOR_SEC = new Color(48, 97, 14);
    private final Color COLOR_INONDE = new Color(101, 147, 99);

    private final int POS_ICN_X = this.BOX_SIZE / 2;
    private final int POS_ICN_Y = this.BOX_SIZE / 2 + 10;

    private Modele modele;
    private Case c;
    private JPanel panel = new JPanel();
    private JLabel icn_joueur = new JLabel(
            Utils.scaleImg(Constants.ICN_JOUEUR, this.POS_ICN_X, this.POS_ICN_Y));
    private JLabel icn_objet = new JLabel();

    public VueCase(Modele m, Case c) {
        this.c = c;

        this.modele = m;
        this.modele.addObserver(this);

        this.setLayout(new FlowLayout());    
        this.setPreferredSize(new Dimension(this.BOX_SIZE, this.BOX_SIZE));
        this.paintJoueurs();
        this.paintObjet();

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
    }

    private void paintJoueurs() {
        this.panel.remove(this.icn_joueur);

        if (!this.modele.getJoueurActuel().estVivant()) {
            this.icn_joueur.setIcon(Utils.scaleImg(Constants.ICN_TOMB, this.POS_ICN_X, this.POS_ICN_Y));
            this.panel.add(this.icn_joueur);
        }

        else if (this.caseJoueur()) {
            this.panel.add(this.icn_joueur);
        }
    }

    private void paintObjet() {
        this.panel.remove(this.icn_objet);
        // this.icn_objet.setVisible(this.c.getObjetVisibilite());
        
        int POS_ICN_X = this.POS_ICN_X;
        int POS_ICN_Y = this.POS_ICN_Y;

        if (this.caseJoueur()) {
            POS_ICN_X = this.POS_ICN_X / 2;
            POS_ICN_Y = this.POS_ICN_Y / 2;   
        }

        if (this.c.helipad) {
            this.icn_objet.setIcon(Utils.scaleImg(Constants.ICN_HELICOPTER, POS_ICN_X, POS_ICN_Y));
            this.panel.add(this.icn_objet);
        }

        if (this.c.aObjet(Clef.class)) {
            this.icn_objet
                    .setIcon(Utils.scaleImg(Constants.ICN_CLEF, POS_ICN_X, POS_ICN_Y));

            this.panel.add(this.icn_objet);
        }

        else if (this.c.aObjet(Artefact.class)) {
            switch (this.c.getObjet().element) {
                case EAU:
                    this.icn_objet
                            .setIcon(Utils.scaleImg(Constants.ICN_EAU, POS_ICN_X, POS_ICN_Y));
                    break;
                case AIR:
                    this.icn_objet
                            .setIcon(Utils.scaleImg(Constants.ICN_AIR, POS_ICN_X, POS_ICN_Y));
                    break;
                case TERRE:
                    this.icn_objet
                            .setIcon(Utils.scaleImg(Constants.ICN_TERRE, POS_ICN_X, POS_ICN_Y));
                    break;
                case FEU:
                    this.icn_objet
                            .setIcon(Utils.scaleImg(Constants.ICN_FEU, POS_ICN_X, POS_ICN_Y));
                    break;

                default:
                    break;
            }

            this.panel.add(this.icn_objet);
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

    private boolean caseJoueur(){
        return this.modele.getJoueurActuel().getCoord() == this.c.coord;
    }
}
