package Modele;

import java.util.Arrays;
import java.util.List;

/**
 * Un point de composantes positives dans un plan orthonormé. Dans ce plan,
 * l'origine est au plus haut-gauche,
 * la direction d'abscisses est vers la droite et la direction d'ordonnées est
 * vers le bas.
 */
public class Coord {
    /**
     * L'abscisse du point. Il est toujours positif.
     */
    private final int x;

    /**
     * L'ordonnée du point. Elle est toujours positive.
     */
    private final int y;

    /**
     * Construit un point de composantes positives dans un plan orthonormé.
     * 
     * @param x L'abscisse.
     * @param y L'ordonnée.
     * @throws IllegalArgumentException Si ni {@code x} ni {@code y} sont pas
     *                                  positives.
     */
    public Coord(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(String.format("x = %d ou y = %d n'est pas positif.", x, y));
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Renvoie l'abscisse du point.
     * 
     * @return L'abscisse du point.
     */
    public int x() {
        return this.x;
    }

    /**
     * Renvoie l'ordonnée du point.
     * 
     * @return L'ordonnée du point.
     */
    public int y() {
        return this.y;
    }

    /**
     * Renvoie les coordonnées du point en chaine de caractères.
     * 
     * @return Les coordonnées du point en chaine de caractères.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

    /**
     * Vérifie si deux points sont le même.
     * 
     * @param obj L'autre point.
     * @return Vrai si {@code this} et {@code obj} est le point (pas nécessairement
     *         le même objet), Faux sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
        Coord c = (Coord) obj;
        return this.x == c.x() && this.y == c.y();
    }

    /**
     * Renvoie le code de hachage d'un point.
     * 
     * @return Le code de hachage d'un point.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.y;
        return result;
    }

    /**
     * Renvoie le point adjacent au point actuel.
     * 
     * @param dir {@link Direction} du point.
     * @return Le point adjacent.
     */
    public Coord adjacent(Direction dir) {
        switch (dir) {
            case HAUT:
                return new Coord(this.x(), this.y() - 1);
            case BAS:
                return new Coord(this.x(), this.y() + 1);
            case GAUCHE:
                return new Coord(this.x() - 1, this.y());
            case DROITE:
                return new Coord(this.x() + 1, this.y());
            case NEUTRE:
                return this;
            default:
                throw new IllegalArgumentException("La direction n'est pas reconnu.");
        }
    }

    /**
     * Renvoie si le point est adjacent.
     * 
     * @param c                 Le point.
     * @param neutreEstAdjacent True considére neutre comme adjacents. Sinon False
     * @return True si le point est adjacent. False sinon.
     */
    public boolean estAdjacent(Coord c, boolean neutreEstAdjacent) {
        List<Coord> adjacents = Arrays.asList(adjacent(Direction.HAUT),
                adjacent(Direction.BAS),
                adjacent(Direction.DROITE),
                adjacent(Direction.GAUCHE));

        if (neutreEstAdjacent)
            adjacents.add(adjacent(Direction.NEUTRE));

        return adjacents.contains(c);
    }

    /**
     * Renvoie si le point est adjacent.
     * 
     * @param c Le point.
     * @return True si le point est adjacent. False sinon.
     */
    public boolean estAdjacent(Coord c) {
        return this.estAdjacent(c, true);
    }

    /**
     * Renvoie la direction du point.
     * 
     * @param coord
     * @return
     */
    public Direction adjacentDir(Coord coord) {
        if (coord.equals(new Coord(this.x(), this.y() - 1)))
            return Direction.HAUT;

        else if (coord.equals(new Coord(this.x(), this.y() + 1)))
            return Direction.BAS;

        else if (coord.equals(new Coord(this.x() - 1, this.y())))
            return Direction.GAUCHE;

        else if (coord.equals(new Coord(this.x() + 1, this.y())))
            return Direction.DROITE;

        else if (coord.equals(new Coord(this.x(), this.y())))
            return Direction.NEUTRE;

        else
            throw new IllegalArgumentException("La case n'est pas adjacente.");
    }
}
