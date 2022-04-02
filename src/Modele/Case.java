package Modele;

import javafx.util.Pair;

public class Case {
    private boolean helicopter;
    private boolean tresor;
    private int positionX;
    private int positionY;
    private Inondation etat = Inondation.SECHE;

    public Case(int i, int j) {
        this.positionX = i;
        this.positionY = j;
    }
}
