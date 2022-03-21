package Modele;

import Modele.Case;

public class Grille {
    private int hauteur, largeur;
    private Case[][] cases;

    public Grille(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        cases = new Case[this.hauteur][this.largeur];
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                cases[i][j] = new Case(i, j);
            }
        }
    }
}