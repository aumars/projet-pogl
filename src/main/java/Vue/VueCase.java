package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCase extends JPanel implements Observer {
    private int ICN_SIZEX = ConstsValue.BOX_SIZE / 2;
    private int ICN_SIZEY = ConstsValue.BOX_SIZE / 2 + 10;

    private Modele modele;
    private Case c;
    private JPanel tuile_case = new JPanel();
    private JLabel icn_avatar = new JLabel(Utils.tailleImg(ConstsIcon.AVATAR, this.ICN_SIZEX, this.ICN_SIZEY));
    private JLabel icn_objet = new JLabel();

    public VueCase(Modele m, Case c) {
        this.c = c;

        this.modele = m;
        this.modele.addObserver(this);

        if (this.estCaseJoueur()) {
            this.ICN_SIZEX = this.ICN_SIZEX / 2;
            this.ICN_SIZEY = this.ICN_SIZEY / 2;
        }

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE, ConstsValue.BOX_SIZE));
        this.afficheJoueur();
        this.afficheObjet();

        this.tuile_case.setOpaque(false);
        this.tuile_case.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE, ConstsValue.BOX_SIZE));
        this.add(this.tuile_case);
    }

    public void metAJourApresAction() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        colorieSol(g);
    }

    private void afficheJoueur() {
        this.tuile_case.remove(this.icn_avatar);

        if (!this.modele.getJoueurActuel().estVivant()) {
            this.icn_avatar.setIcon(Utils.tailleImg(ConstsIcon.TOMBE, this.ICN_SIZEX, this.ICN_SIZEY));
            this.tuile_case.add(this.icn_avatar);
        }

        else if (this.estCaseJoueur()) {
            this.tuile_case.add(this.icn_avatar);
        }
    }

    private void afficheObjet() {
        this.tuile_case.remove(this.icn_objet);
        this.icn_objet.setVisible(this.c.getObjetVisibilite());

        if (this.c.estHelipad()) {
            this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.HELICOPTERE, ICN_SIZEX, ICN_SIZEY));
            this.tuile_case.add(this.icn_objet);
        }

        else if (this.c.aObjet(Clef.class)) {
            this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.CLEF, ICN_SIZEX, ICN_SIZEY));

            this.tuile_case.add(this.icn_objet);
        }

        else if (this.c.aObjet(Artefact.class)) {
            switch (this.c.getObjet().element) {
                case EAU:
                    this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.EAU, ICN_SIZEX, ICN_SIZEY));
                    break;

                case AIR:
                    this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.AIR, ICN_SIZEX, ICN_SIZEY));
                    break;

                case TERRE:
                    this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.TERRE, ICN_SIZEX, ICN_SIZEY));
                    break;

                case FEU:
                    this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.FEU, ICN_SIZEX, ICN_SIZEY));
                    break;

                default:
                    break;
            }

            this.tuile_case.add(this.icn_objet);
        }
    }

    private void colorieSol(Graphics g) {
        if (this.c.terrain == Terrain.MER)
            g.setColor(ConstsValue.COLOR_MER);

        else if (this.c.getObjetVisibilite() && this.c.estHelipad())
            g.setColor(Color.GRAY);

        else {
            switch (this.c.getEtat()) {
                case SECHE:
                    g.setColor(ConstsValue.COLOR_SEC);
                    break;

                case INONDEE:
                    g.setColor(ConstsValue.COLOR_INONDE);
                    break;

                case SUBMERGEE:
                    g.setColor(ConstsValue.COLOR_MER);
                    break;

                default:
                    g.setColor(ConstsValue.COLOR_DEFAULT);
                    break;
            }
        }

        g.fillRect(0, 0, ConstsValue.BOX_SIZE, ConstsValue.BOX_SIZE);
    }

    private boolean estCaseJoueur() {
        return this.modele.getJoueurActuel().getCoord() == this.c.coord;
    }
}
