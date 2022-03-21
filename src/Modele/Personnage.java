package Modele;

import javafx.util.Pair;

public class Personnage {
    private boolean estVivant = true;
    private Pair<Integer, Integer> position;
    private boolean aTresor = false;
    private boolean aGagne = false;

    public Personnage(int x, int y) {
        this.position = new Pair<>(x, y);
    }

    public void meurt() {
        this.estVivant = false;
    }


}
