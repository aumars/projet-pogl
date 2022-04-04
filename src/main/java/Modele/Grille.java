package Modele;

import javafx.util.Pair;

import java.util.List;

public class Grille {
    private int hauteur, largeur;
    private Case[][] cases;

    public Grille(int hauteur, int largeur, char[][] map, List<Pair<Objet, Coord>> objets) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.cases = new Case[this.hauteur][this.largeur];
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                this.cases[i][j] = new Case(i, j, map[i][j]);
            }
        }

        for (Pair<Objet, Coord> p: objets) {
            Objet o = p.getKey();
            Coord c = p.getValue();
            this.cases[c.y()][c.x()].ajoutObjet(o);
        }
    }

    public boolean estTraversable(int x, int y) {
        return x >= 0 && y >= 0
                && y < this.hauteur && x < this.largeur
                && this.cases[y][x].estTraversable();
    }
}
