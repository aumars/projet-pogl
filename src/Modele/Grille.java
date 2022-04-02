package Modele;

public class Grille {
    private int hauteur, largeur;
    private Case[][] cases;

    public Grille(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.cases = new Case[this.hauteur][this.largeur];
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                this.cases[i][j] = new Case(i, j, i >= 1 && i <= hauteur - 1 && j >= 1 && j <= largeur - 1, i == 4 && j == 5);
            }
        }

        this.cases[2][3].ajoutObjet(new Artefact(Element.AIR));
        this.cases[3][4].ajoutObjet(new Artefact(Element.EAU));
        this.cases[6][4].ajoutObjet(new Artefact(Element.TERRE));
        this.cases[5][1].ajoutObjet(new Artefact(Element.FEU));

        this.cases[4][2].ajoutObjet(new Clef(Element.AIR));
        this.cases[5][6].ajoutObjet(new Clef(Element.EAU));
        this.cases[5][3].ajoutObjet(new Clef(Element.TERRE));
        this.cases[1][6].ajoutObjet(new Clef(Element.FEU));
    }

    public boolean estTraversable(int x, int y) {
        return x >= 0 && y >= 0
                && x < this.hauteur && y < this.largeur
                && this.cases[x][y].estTraversable();
    }
}
