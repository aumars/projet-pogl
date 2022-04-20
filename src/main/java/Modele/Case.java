package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Une case de la grille.
 */
public class Case {
    /**
     * Les coordonnées de la case. L'origine est la case le plus à haut-gauche.
     */
    public final Coord coord;

    /**
     * Le terrain de la case.
     */
    public final Terrain terrain;

    /**
     * L'état d'inondation de la case.
     */
    private Inondation etat;

    /**
     * L'objet qui est sur la case. On ne peut avoir qu'un seul objet.
     */
    private Objet objet;

    /**
     * Drapeau pour marquer la visibilité de l'objet de la case sur la grille.
     */
    private boolean objetVisibilite;

    /**
     * La grille dans se trouve la case.
     */
    private final Grille grille;

    private final List<Joueur> joueurs;

    /**
     * Construit une case dans la grille.
     * 
     * @param coord   Coordonnées de la case.
     * @param terrain Terrain de la case.
     * @param g       Grille associée à la case.
     */
    public Case(Coord coord, Terrain terrain, Grille g) {
        this.coord = coord;
        this.terrain = terrain;
        this.grille = g;
        this.joueurs = new ArrayList<>();
        this.setEtat(Inondation.SECHE);
        this.setObjetVisibilite(true);
        this.joueurs.clear();
    }

    /**
     * Redémarre la case.
     */
    public void restart() {
        this.setEtat(Inondation.SECHE);
        if (this.aObjet()) {
            this.setObjetVisibilite(!this.getObjet().getClass().equals(Clef.class));
        }
        this.joueurs.clear();
    }

    /**
     * Ajoute un Joueur
     * 
     * @param j Un Joueur
     */
    public void setJoueur(Joueur j) {
        this.joueurs.add(j);
    }

    /**
     * Supprime un Joueur
     * 
     * @param j Un Joueur
     */
    public void removeJoueur(Joueur j) {
        this.joueurs.remove(j);
    }

    /**
     * Renvoie la liste de joueurs sur la case.
     */
    public List<Joueur> getJoueurs() {
        return this.joueurs;
    }

    @Override
    public String toString() {
        return "Case " + this.coord.toString();
    }

    /**
     * Vérifie si la case est traversable par un Joueur.
     * 
     * @return Vrai la case est traversable par un Joueur, Faux sinon.
     */
    public boolean estTraversable() {
        return this.terrain == Terrain.HELIPAD || (this.terrain == Terrain.TERRE && this.etat != Inondation.SUBMERGEE);
    }

    /**
     * Vérifie si la case a un helipad.
     * 
     * @return True si la case a un helipad, Faux sinon.
     */
    public boolean estHelipad() {
        return this.terrain == Terrain.HELIPAD;
    }

    /**
     * Ajouter un objet sur la case. Si l'objet est une Clef, la visibilité de
     * l'objet est initialisée à Faux.
     * 
     * @param o Un objet.
     * @throws RuntimeException Si la case n'est pas vide.
     * @throws RuntimeException Si la case n'a pas un terrain de type TERRE.
     */
    public void ajoutObjet(Objet o) {
        if (this.objet != null) {
            throw new RuntimeException(String.format("Il existe déjà un objet dans %s", this));
        } else if (this.terrain != Terrain.TERRE) {
            throw new RuntimeException(String.format("%s n'a pas un terrain de type TERRE.", this));
        } else {
            this.objet = o;
            this.setObjetVisibilite(!this.objet.getClass().equals(Clef.class));
            System.out.printf("%s: %s ajouté%n", this, this.objet);
        }
    }

    /**
     * Vérifie si un objet existe sur la case.
     * 
     * @return Vrai si un objet existe sur la case, Faux sinon.
     */
    public boolean aObjet() {
        return this.objet != null;
    }

    /**
     * Vérifie si un objet de classe spécifiée existe sur la case.
     * 
     * @param c Classe
     * @return Vrai si si un objet de classe {@code c} existe sur la case, Faux
     *         sinon.
     */
    public boolean aObjet(Class<?> c) {
        return this.objet != null && this.objet.getClass().equals(c);
    }

    /**
     * Renvoie l'objet sur la case.
     * 
     * @return L'objet sur la case.
     */
    public Objet getObjet() {
        return this.objet;
    }

    /**
     * Renvoie la visibilité de l'objet sur la case, même s'il n'y a pas d'objet sur
     * la case.
     * 
     * @return La visibilité de l'objet sur la case, même s'il n'y a pas d'objet sur
     *         la case.
     */
    public boolean getObjetVisibilite() {
        return this.objetVisibilite;
    }

    /**
     * Modifie la visibilité de l'objet sur la grille.
     * 
     * @param b La visibilité de l'objet.
     */
    public void setObjetVisibilite(boolean b) {
        this.objetVisibilite = b;
        System.out.printf("Case %s: Visibilité est %b%n", this.coord, b);
    }

    /**
     * Détruit l'objet sur la grille, même s'il n'existe pas.
     */
    public void detruitObjet() {
        this.objet = null;
        this.setObjetVisibilite(true);
    }

    /**
     * Renvoie l'état d'inondation de la case.
     * 
     * @return L'état d'inondation de la case.
     */
    public Inondation getEtat() {
        return this.etat;
    }

    /**
     * Modifié l'état d'inondation d'une Case.
     * 
     * @param etat Nouvel état d'inondation
     */
    public void setEtat(Inondation etat) {
        this.etat = etat;
    }

    /**
     * Réduit l'état d'inondation de la case.
     * 
     * @return Vrai si le séchage est réussi, Faux sinon.
     */
    public boolean asseche() {
        switch (this.getEtat()) {
            case SECHE:
                return true;
            case INONDEE:
                this.setEtat(Inondation.SECHE);
                return true;
            case SUBMERGEE:
            default:
                return false;
        }
    }

    /**
     * Augmente l'état d'inondation de la case.
     */
    public void monteEaux() {
        switch (this.getEtat()) {
            case SECHE:
                this.setEtat(Inondation.INONDEE);
                break;
            case INONDEE:
                this.setEtat(Inondation.SUBMERGEE);
                break;
            default:
                break;
        }
    }

    /**
     * Renvoie la case adjacente de la case actuelle par rapport à sa direction.
     * 
     * @param dir Direction de la case adjacente.
     * @return La case adjacente.
     * @throws RuntimeException         Si la case n'est pas associée à une grille.
     * @throws IllegalArgumentException Si {@code dir} n'est pas reconnu.
     */
    public Case adjacent(Direction dir) {
        if (dir == Direction.NEUTRE) {
            return this;
        } else if (this.grille != null) {
            return this.grille.getCase(this.coord.adjacent(dir));
        } else {
            throw new RuntimeException("La case n'est pas associée à une grille.");
        }
    }

    /**
     * Vérifie si la case actuelle est ses cases adjacentes ne sont pas
     * traversables.
     * 
     * @return Vrai si la case actuelle est ses cases adjacentes ne sont pas
     *         traversables, Faux sinon.
     */
    public boolean adjacentSubmergee() {
        return !adjacent(Direction.HAUT).estTraversable()
                && !adjacent(Direction.BAS).estTraversable()
                && !adjacent(Direction.GAUCHE).estTraversable()
                && !adjacent(Direction.DROITE).estTraversable();
    }
}
