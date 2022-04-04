package Modele;

import java.util.HashSet;
import java.util.Set;

class Joueur extends Personnage {
    private Set<Objet> inventaire;

    public Joueur(int x, int y) {
        super(x, y);
        inventaire = new HashSet<>();
    }

    public Joueur(Coord c) {
        this(c.x(), c.y());
    }
}
