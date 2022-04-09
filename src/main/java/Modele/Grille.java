package Modele;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;

public class Grille {
    private final Case[][] cases;
    private final List<Case> ile;

    public Grille(Carte carte) {
        this(carte.map);
    }

    public Grille(char[][] map) {
        this.cases = new Case[map.length][map[0].length];
        this.ile = new ArrayList<>();
        for (int i = 0; i < this.cases.length; i++) {
            for (int j = 0; j < this.cases[0].length; j++) {
                Case c = new Case(i, j, map[i][j], this);
                this.cases[i][j] = c;
                if (c.terrain == Terrain.TERRE) {
                    this.ile.add(c);
                }
            }
        }
    }

    public Grille(char[][] map, List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> objets) {
        this(map);
        this.addObjets(objets);
    }

    public void addObjet(AbstractMap.SimpleImmutableEntry<Objet, Coord> p) {
        Objet o = p.getKey();
        Coord c = p.getValue();
        this.cases[c.y()][c.x()].ajoutObjet(o);
    }

    public void addObjets(List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> objets) {
        for (AbstractMap.SimpleImmutableEntry<Objet, Coord> p: objets) {
            this.addObjet(p);
        }
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
