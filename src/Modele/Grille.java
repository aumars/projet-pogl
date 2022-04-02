package Modele;

public class Grille {
    private int hauteur, largeur;
    private Case[][] cases;

    public Grille(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        cases = new Case[this.hauteur][this.largeur];
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                cases[i][j] = new Case(i, j, i >= 2 && i <= hauteur - 2 && j >= 2 && j <= largeur - 2);
            }
        }
    }

    public boolean estTraversable(int x, int y) {
        return x >= 0 && y >= 0
                && x < this.hauteur && y < this.largeur
                && this.cases[x][y].estTraversable();
    }
}
