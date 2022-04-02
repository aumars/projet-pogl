package Modele;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Modele extends Observable {
    private static final int largeur = 8, hauteur = 8;
    private Grille grille;
    private Set<Personnage> ensemble;
    private Joueur joueur;

    public Modele() {
        this.grille = new Grille(hauteur, largeur);
        this.initialise();
    }

    public void initialise() {
        this.joueur = new Joueur(hauteur-2, largeur-2);
        this.ensemble = new HashSet<>();
        this.ensemble.add(this.joueur);
    }

    public boolean deplaceJoueur(Direction dir) {
        int x = this.joueur.posX();
        int y = this.joueur.posY();
        switch (dir) {
            case HAUT: x--; break;
            case BAS: x++; break;
            case GAUCHE: y--; break;
            case DROITE: y++; break;
        }
        if (this.grille.estTraversable(x, y)) {
            this.joueur.deplace(dir);
            return true;
        }
        else {
            return false;
        }
    }
}