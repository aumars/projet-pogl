package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

/**
 * L'interface graphique de la grille
 */
public class VueGrille extends JPanel {
    /**
     * Largeur de la grille
     */
    private final int WIDTH;

    /**
     * Hauteur de la grille
     */
    private final int HEIGHT;

    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * Une matrice d'interfaces graphiques de chaque case de la grille.
     */
    private final VueCase[][] GRILLE;

    /**
     * Affichage d'une grille.
     * 
     * @param m Le modele.
     */
    public VueGrille(Modele m) {
        this.MODELE = m;

        this.WIDTH = this.MODELE.getGrille().getWidth();
        this.HEIGHT = this.MODELE.getGrille().getHeight();

        this.GRILLE = new VueCase[this.WIDTH][this.HEIGHT];

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, ConstsValue.BORDER_GRID, 0, ConstsValue.BORDER_GRID));
        this.afficheGrille();
    }

    /**
     * Affiche la grille.
     */
    private void afficheGrille() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.MODELE.getGrille().getCase(j, i);
                this.GRILLE[i][j] = new VueCase(this.MODELE, c);
                this.add(this.GRILLE[i][j], Utils.positionneGrille(j, i, ConstsValue.GAP_CASE));
            }
        }
    }

    /**
     * Mets à jour l'affichage d'une case de la grille.
     * 
     * @param coord La coordonnées de la case.
     */
    public void metAJourCase(Coord coord) {
        Case c = this.MODELE.getGrille().getCase(coord);

        this.remove(this.GRILLE[coord.y()][coord.x()]);
        this.GRILLE[coord.y()][coord.x()] = new VueCase(this.MODELE, c);
        this.add(this.GRILLE[coord.y()][coord.x()], Utils.positionneGrille(coord.x(), coord.y(), ConstsValue.GAP_CASE));

        this.revalidate();
        this.repaint();
    }

    /**
     * Mets à jours les cases lorsqu'un joueur se déplace.
     * 
     * @param prev Les coordonnées de la case initiale du joueur.
     * @param next Les coordonnées de la case où le joueur se déplace.
     */
    public void metAJourDeplacementJoueur(Coord prev, Coord next) {
        Case c_prev = this.MODELE.getGrille().getCase(prev);
        Case c_next = this.MODELE.getGrille().getCase(next);

        this.remove(this.GRILLE[prev.y()][prev.x()]);
        this.remove(this.GRILLE[next.y()][next.x()]);

        this.GRILLE[prev.y()][prev.x()] = new VueCase(this.MODELE, c_prev);
        this.GRILLE[next.y()][next.x()] = new VueCase(this.MODELE, c_next);

        this.add(this.GRILLE[prev.y()][prev.x()], Utils.positionneGrille(prev.x(), prev.y(), ConstsValue.GAP_CASE));
        this.add(this.GRILLE[next.y()][next.x()], Utils.positionneGrille(next.x(), next.y(), ConstsValue.GAP_CASE));

        this.revalidate();
        this.repaint();
    }
}
