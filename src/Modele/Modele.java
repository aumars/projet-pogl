package Modele;

import java.util.Observable;
import java.util.Set;

public class Modele extends Observable {
    private static final int largeur = 8, hauteur = 8;
    private Grille grille;
    private Set<Personnage> ensemble;
    private Joueur joueur;
    public Modele() {
        this.grille = new Grille(hauteur, largeur);
    }
}