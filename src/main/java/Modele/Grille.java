package Modele;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;

public class Grille {
    private int hauteur, largeur;
    private Case[][] cases;
    private List<Case> ile;

    public Grille(int hauteur, int largeur, char[][] map, List<Pair<Objet, Coord>> objets) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.cases = new Case[this.hauteur][this.largeur];
        this.ile = new ArrayList<>();
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                Case c = new Case(i, j, map[i][j]);
                this.cases[i][j] = c;
                if (c.terrain == Terrain.TERRE) {
                    this.ile.add(c);
                }
            }
        }

        for (Pair<Objet, Coord> p: objets) {
            Objet o = p.getKey();
            Coord c = p.getValue();
            this.cases[c.y()][c.x()].ajoutObjet(o);
        }
    }

    public boolean estTraversable(int x, int y) {
        return x >= 0 && y >= 0
                && y < this.hauteur && x < this.largeur
                && this.cases[y][x].estTraversable();
    }

    public Case getCase(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("");
        }
        return this.cases[y][x];
    }

    public Case getCase(Coord c) {
        return this.cases[c.y()][c.x()];
    }

    public void inonde() {
        Random rng = new Random();
        int case1 = (int) floor(rng.nextDouble() * (this.ile.size() - 1));
        int case2 = (int) floor(rng.nextDouble() * (this.ile.size() - 1));
        int case3 = (int) floor(rng.nextDouble() * (this.ile.size() - 1));
        this.ile.get(case1).monteEaux();
        this.ile.get(case2).monteEaux();
        this.ile.get(case3).monteEaux();
    }
}
