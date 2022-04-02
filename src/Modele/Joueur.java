package Modele;

import java.util.HashSet;
import java.util.Set;

class Joueur extends Personnage {
    private Set<Objet> inventaire;
    private boolean aGagne = false;

    public Joueur(int x, int y) {
        super(x, y);
        inventaire = new HashSet<>();
    }
}
