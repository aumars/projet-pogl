package Vue;

import Modele.*;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * L'interface graphique d'une case
 */
public class VueCase extends JPanel implements Observer {
    /**
     * Largeur de la case
     */
    private int ICN_SIZEX = ConstsValue.BOX_SIZE / 2;
    /**
     * Hauteur de la case
     */
    private int ICN_SIZEY = ConstsValue.BOX_SIZE / 2;

    /**
     * Nombre de joueurs sur la case
     */
    private final int NB_JOUEURS;

    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * La case que l'interface graphique représente
     */
    private final Case CASE;

    /**
     * JLabel des objets/Joueurs sur la case.
     */
    private final JLabel ICN_OBJET = new JLabel();

    /**
     * Affichage d'une case de la grille.
     * 
     * @param m Le modele.
     * @param c La case dans la grille du jeu.
     */
    public VueCase(Modele m, Case c) {
        this.MODELE = m;
        this.CASE = c;
        this.MODELE.addObserver(this);
        this.NB_JOUEURS = this.getJoueurs().size();

        this.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE, ConstsValue.BOX_SIZE));

        this.metAJourTailleIcon();
        this.afficheTousJoueurs();
        this.afficheObjet();
    }

    /**
     * Mets a jours l'affichage du bouton.
     */
    public void metAJourApresAction() {
        this.repaint();
    }

    /**
     * Paint la case.
     */
    public void paintComponent(Graphics g) {
        super.repaint();
        this.colorieSol(g);
        this.dessineCaseAdjacent(g);
    }

    /**
     * Affiche un joueurs sur la case.
     * 
     * @param j Le joueurs à afficher
     */
    private void afficheUnJoueur(Joueur j) {
        if (!j.estVivant()) {
            this.add(new JLabel(Utils.tailleImg(ConstsIcon.TOMBE, this.ICN_SIZEX, this.ICN_SIZEY)));
        } else {
            this.add(new JLabel(Utils.tailleImg(ConstsIcon.getImgAvatar(j.ID), this.ICN_SIZEX, this.ICN_SIZEY)));
        }
    }

    /**
     * Affiche tous les joueurs sur la case.
     */
    private void afficheTousJoueurs() {
        this.getJoueurs().forEach(this::afficheUnJoueur);
    }

    /**
     * Affiche un objet sur la case.
     */
    private void afficheObjet() {
        this.remove(this.ICN_OBJET);
        this.ICN_OBJET.setVisible(this.CASE.getObjetVisibilite());

        if (this.CASE.estHelipad()) {
            this.ICN_OBJET.setIcon(Utils.tailleImg(ConstsIcon.HELICOPTERE, ICN_SIZEX, ICN_SIZEY));
            this.add(this.ICN_OBJET);
        }

        else if (this.CASE.aObjet()) {
            VueObjet objet = new VueObjet(this.CASE.getObjet(), new Color(255, 255, 255), false);
            objet.setOpaque(false);
            objet.setVisible(this.CASE.getObjetVisibilite());
            this.add(objet);
        }
    }

    /**
     * Colorie le sol en fonction de son état.
     * 
     * @param g Le graphique.
     */
    private void colorieSol(Graphics g) {
        if (this.CASE.TERRAIN == Terrain.MER)
            g.setColor(ConstsValue.COLOR_MER);

        else if (this.CASE.getObjetVisibilite() && this.CASE.estHelipad())
            g.setColor(Color.GRAY);

        else {
            switch (this.CASE.getEtat()) {
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

    /**
     * Renvoie si un joueur est sur cette case.
     * 
     * @return True si la case contient un joueur. False sinon.
     */
    public boolean estCaseJoueur() {
        return this.getJoueurs().size() != 0;
    }

    /**
     * Recupere tous les joueurs présents sur la case.
     * 
     * @return Une liste des joueurs sur la case.
     */
    private List<Joueur> getJoueurs() {
        List<Joueur> joueurs = new ArrayList<>();

        for (Joueur joueur : this.MODELE.getJoueurs())
            if (joueur.getCoord().equals(this.CASE.COORD))
                joueurs.add(joueur);

        return joueurs;
    }

    /**
     * Met a jour la taille des icones de la case.
     */
    private void metAJourTailleIcon() {
        if (this.estCaseJoueur()) {
            this.ICN_SIZEX = this.ICN_SIZEX / (this.NB_JOUEURS + 1) + this.ICN_SIZEX / 3;
            this.ICN_SIZEY = this.ICN_SIZEY / (this.NB_JOUEURS + 1) + this.ICN_SIZEY;
        }
    }

    /**
     * Dessine un point sur la case le joueur peut jouet et
     * si elle est traversable et adjacente au joueur.
     * 
     * @param g Le graphique.
     */
    private void dessineCaseAdjacent(Graphics g) {
        Joueur joueur = this.MODELE.getJoueurActuel();

        if (joueur.estSonTour() && !joueur.aZeroAction() && joueur.getCoord().estAdjacent(this.CASE.COORD) && this.CASE.estTraversable()) {
            int x = ConstsValue.BOX_SIZE / 2;
            int y = ConstsValue.BOX_SIZE / 2;
            int radius = ConstsValue.BOX_SIZE / 8;
            int diameter = radius * 2;

            g.setColor(Color.YELLOW);
            g.fillOval(x - radius, y - radius, diameter, diameter);
        }
    }
}
