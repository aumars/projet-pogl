package Vue;

import Modele.*;
import javax.swing.*;
import java.awt.*;

public class VueGrille extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;
    private final int GAP_BORDER = 10;

    private Modele modele;
    private VueCase[][] grille;

    public VueGrille(Modele m) {
        this.modele = m;

        this.WIDTH = this.modele.getGrille().getWidth();
        this.HEIGHT = this.modele.getGrille().getHeight();

        this.grille = new VueCase[this.WIDTH][this.HEIGHT];

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, this.GAP_BORDER, 0, this.GAP_BORDER));
        this.afficheGrille();
    }

    public void afficheGrille() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.modele.getGrille().getCase(j, i);
                this.grille[i][j] = new VueCase(this.modele, c);
                this.add(this.grille[i][j], Utils.positionneGrille(j, i, ConstsValue.GAP_CASE));
            }
        }
    }

    public void metAJourToutesCases() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                this.remove(this.grille[i][j]);
                Case c = this.modele.getGrille().getCase(j, i);
                this.grille[i][j] = new VueCase(this.modele, c);
                this.add(this.grille[i][j], Utils.positionneGrille(j, i, ConstsValue.GAP_CASE));
            }
        }
    }

    public void metAJourCase(Coord coord) {
        Case c = this.modele.getGrille().getCase(coord);

        this.remove(this.grille[coord.y()][coord.x()]);
        this.grille[coord.y()][coord.x()] = new VueCase(this.modele, c);
        this.add(this.grille[coord.y()][coord.x()], Utils.positionneGrille(coord.x(), coord.y(), ConstsValue.GAP_CASE));

        this.revalidate();
        this.repaint();
    }

    public void metAJourDeplacementJoueur(Coord prev, Coord next) {
        Case c_prev = this.modele.getGrille().getCase(prev);
        Case c_next = this.modele.getGrille().getCase(next);

        this.remove(this.grille[prev.y()][prev.x()]);
        this.remove(this.grille[next.y()][next.x()]);

        this.grille[prev.y()][prev.x()] = new VueCase(this.modele, c_prev);
        this.grille[next.y()][next.x()] = new VueCase(this.modele, c_next);

        this.add(this.grille[prev.y()][prev.x()], Utils.positionneGrille(prev.x(), prev.y(), ConstsValue.GAP_CASE));
        this.add(this.grille[next.y()][next.x()], Utils.positionneGrille(next.x(), next.y(), ConstsValue.GAP_CASE));

        this.revalidate();
        this.repaint();
    }
}
