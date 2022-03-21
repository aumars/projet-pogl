package Modele;

import javafx.util.Pair;

public class Case {
    enum Inondation {
        NORMALE,
        INONDE,
        SUBMERGEE
    };
    private boolean helicopter;
    private boolean tresor;
    private Inondation etat = Inondation.NORMALE;
    private Pair<Integer, Integer> position;

    public Case(int i, int j) {
        this.position = new Pair<>(i, j);
    }
}
