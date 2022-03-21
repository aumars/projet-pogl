package Modele;
import java.util.*;

public class Modele extends Observable {
    public static final int HAUTEUR=8, LARGEUR=8;
    private Grille grille;

    public Modele() {
        this.grille = new Grille(this.HAUTEUR, this.LARGEUR);
    }
}