package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCase extends JPanel implements Observer {
    private int ICN_SIZEX = ConstsValue.BOX_SIZE / 2;
    private int ICN_SIZEY = ConstsValue.BOX_SIZE / 2;
    private int nb_joueurs;

    private final Case c;
    private final JLabel icn_objet = new JLabel();

    public VueCase(Modele m, Case c) {
        this.c = c;
        m.addObserver(this);
        this.nb_joueurs = this.c.getJoueurs().size();

        this.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE, ConstsValue.BOX_SIZE));

        this.metAJourTailleIcon();
        this.afficheTousJoueurs();
        this.afficheObjet();
    }

    public void metAJourApresAction() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        colorieSol(g);
    }

    private void afficheUnJoueur(Joueur j) {
        if (!j.estVivant()) {
            this.add(new JLabel(Utils.tailleImg(ConstsIcon.TOMBE, this.ICN_SIZEX, this.ICN_SIZEY)));
        }
        else {
            this.add(new JLabel(Utils.tailleImg(ConstsIcon.getImgAvatar(j.id), this.ICN_SIZEX, this.ICN_SIZEY)));
        }
    }

    private void afficheTousJoueurs() {
        this.c.getJoueurs().forEach(this::afficheUnJoueur);
    }

    private void afficheObjet() {
        this.remove(this.icn_objet);
        this.icn_objet.setVisible(this.c.getObjetVisibilite());

        if (this.c.estHelipad()) {
            this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.HELICOPTERE, ICN_SIZEX, ICN_SIZEY));
            this.add(this.icn_objet);
        }

        else if (this.c.aObjet(Clef.class)) {
            this.icn_objet.setIcon(Utils.tailleImg(ConstsIcon.CLEF, ICN_SIZEX, ICN_SIZEY));

            this.add(this.icn_objet);
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

            this.add(this.icn_objet);
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
        return this.c.getJoueurs().size() != 0;
    }

    private void metAJourTailleIcon() {
        if (this.estCaseJoueur()) {
            this.ICN_SIZEX = this.ICN_SIZEX / (this.nb_joueurs + 1) + this.ICN_SIZEX / 3;
            this.ICN_SIZEY = this.ICN_SIZEY / (this.nb_joueurs + 1) + this.ICN_SIZEY;
        }
    }
}
