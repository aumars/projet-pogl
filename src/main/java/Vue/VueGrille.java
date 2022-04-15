package Vue;

import Modele.*;

import javax.swing.*;

import java.awt.*;

public class VueGrille extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;
    private final int GAP = Constants.GAP_CASE;
    private final int GAP_BORDER = 10;
    private VueCase[][] grille;

    private Modele modele;

    public VueGrille(Modele m) {
        this.modele = m;

        this.WIDTH = this.modele.getGrille().getWidth();
        this.HEIGHT = this.modele.getGrille().getHeight();

        this.grille = new VueCase[this.WIDTH][this.HEIGHT];

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, this.GAP_BORDER, 0, this.GAP_BORDER));
        this.paintGrid();
    }

    public void paintGrid() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.modele.getGrille().getCase(j, i);
                this.grille[i][j] = new VueCase(this.modele, c);
                this.add(this.grille[i][j], Utils.positionGrid(j, i, this.GAP));
            }
        }
    }

    public void updateAllCase(){
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                this.remove(this.grille[i][j]);
                Case c = this.modele.getGrille().getCase(j, i);
                this.grille[i][j] = new VueCase(this.modele, c);
                this.add(this.grille[i][j], Utils.positionGrid(j, i, this.GAP));
            }
        }
    }

    public void updateCase(Coord coord){
        Case c = this.modele.getGrille().getCase(coord);

        this.remove(this.grille[coord.y()][coord.x()]);
        this.grille[coord.y()][coord.x()] = new VueCase(this.modele, c);
        this.add(this.grille[coord.y()][coord.x()], Utils.positionGrid(coord.x(), coord.y(), this.GAP));

        this.revalidate();
        this.repaint();
    }

    public void updatePlayerMove(Coord prev, Coord next) {
        Case c_prev = this.modele.getGrille().getCase(prev);
        Case c_next = this.modele.getGrille().getCase(next);

        this.remove(this.grille[prev.y()][prev.x()]);
        this.remove(this.grille[next.y()][next.x()]);

        this.grille[prev.y()][prev.x()] = new VueCase(this.modele, c_prev);
        this.grille[next.y()][next.x()] = new VueCase(this.modele, c_next);

        this.add(this.grille[prev.y()][prev.x()], Utils.positionGrid(prev.x(), prev.y(), this.GAP));
        this.add(this.grille[next.y()][next.x()], Utils.positionGrid(next.x(), next.y(), this.GAP));

        this.revalidate();
        this.repaint();
    }
}
