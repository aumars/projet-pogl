package Modele;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;

/**
 * La grille.
 */
public class Grille {
    /**
     * Les cases qui composent la grille.
     */
    private final Case[][] CASES;

    /**
     * Les cases qui composent l'ile inondable et traversable.
     */
    private final List<Case> ILE;

    /**
     * La hauteur de la grille.
     */
    private final int HEIGHT;

    /**
     * La largeur de la grille.
     */
    private final int WIDTH;

    /**
     * Construit la grille à partir d'un tableau de tableaux de {@link Terrain}.
     * 
     * @param map Un tableau de tableaux de {@link Terrain}.
     */
    public Grille(Terrain[][] map) {
        this.WIDTH = map[0].length;
        this.HEIGHT = map.length;

        this.CASES = new Case[this.HEIGHT][this.WIDTH];
        this.ILE = new ArrayList<>();
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                this.CASES[i][j] = new Case(new Coord(j, i), map[i][j], this);
            }
        }
        this.remplitIle();
    }

    /**
     * Construit une grille à partir d'un tableau de tableaux de {@link Terrain}
     * avec une liste de pairs d'{@link Objet}
     * et de {@link Coord}.
     *
     * @param map    Un tableau de tableaux de {@link Terrain}.
     * @param objets Une liste de pairs d'{@link Objet} et de {@link Coord}.
     */
    public Grille(Terrain[][] map, List<SimpleImmutableEntry<Objet, Coord>> objets) {
        this(map);
        this.addObjets(objets);
    }

    /**
     * Marquer les cases inondables.
     */
    private void remplitIle() {
        this.ILE.clear();
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.getCase(j, i);
                if (c.TERRAIN == Terrain.TERRE && !c.aObjet()) {
                    this.ILE.add(c);
                }
            }
        }
    }

    /**
     * Rétablit les attributs initiaux de la Grille.
     */
    public void restart() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.getCase(j, i);
                c.restart();
            }
        }
        this.remplitIle();
    }

    /**
     * Ajout un {@link Objet} dans une case spécifiée de la grille.
     * 
     * @param p Un pair d'{@link Objet} et de {@link Coord}.
     * @throws IllegalArgumentException Si le point ne peut pas exister dans la
     *                                  grille.
     */
    public void addObjet(SimpleImmutableEntry<Objet, Coord> p) {
        Objet o = p.getKey();
        Coord c = p.getValue();
        this.addObjet(o, c);
    }

    /**
     * Ajout un {@link Objet} dans une case spécifiée de la grille.
     * 
     * @param o Un objet
     * @param c Un coord
     * @throws IllegalArgumentException Si le point ne peut pas exister dans la
     *                                  grille.
     */
    public void addObjet(Objet o, Coord c) {
        if (c.y() >= this.getHeight() || c.x() >= this.getWidth()) {
            throw new IllegalArgumentException(String.format("Le point %s n'existe pas dans une grille %d * %d.",
                    c, this.getWidth(), this.getHeight()));
        } else {
            this.getCase(c).ajoutObjet(o);
            this.ILE.removeIf(k -> k.COORD.equals(c));
        }
    }

    /**
     * Ajout un {@link Objet} dans une case spécifiée de la grille.
     * 
     * @param objets Une liste de pairs d'{@link Objet} et de {@link Coord}.
     * @throws IllegalArgumentException Si un point ne peut pas exister dans la
     *                                  grille.
     */
    public void addObjets(List<SimpleImmutableEntry<Objet, Coord>> objets) {
        objets.forEach(this::addObjet);
    }

    /**
     * Renvoie la largeur de la grille.
     * 
     * @return La largeur de la grille.
     */
    public int getWidth() {
        return this.WIDTH;
    }

    /**
     * Renvoie la hauteur de la grille.
     * 
     * @return La hauteur de la grille.
     */
    public int getHeight() {
        return this.HEIGHT;
    }

    /**
     * Renvoie la {@link Case} dans des coordonnées données.
     * 
     * @param x L'abscisse.
     * @param y L'ordonnée
     * @return Une {@link Case}
     */
    public Case getCase(int x, int y) {
        return this.getCase(new Coord(x, y));
    }

    /**
     * Renvoie la {@link Case} dans des coordonnées données.
     * 
     * @param c Un {@link Coord} du plan.
     * @return Une {@link Case}.
     */
    public Case getCase(Coord c) {
        return this.CASES[c.y()][c.x()];
    }

    /**
     * Inonde aléatoirement trois cases traversables de la grille.
     */
    public void inonde() {
        if (this.ILE.size() > 0) {
            Random rng = new Random();
            int case1 = (int) floor(rng.nextDouble() * (this.ILE.size() - 1));
            this.ILE.get(case1).monteEaux();
        }
    }
}
