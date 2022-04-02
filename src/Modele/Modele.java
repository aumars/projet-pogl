package Modele;

import java.util.Observable;

public class Modele extends Observable {
    private static final int largeur = 8, hauteur = 8;
    private Grille grille;
    private Joueur joueur;
    public Modele() {
        this.grille = new Grille(hauteur, largeur);
    }
}