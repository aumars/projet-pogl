package Modele;

import Modele.Exception.InvalidGameException;
import java.util.*;

/**
 * Le système de jeu.
 */
public class Modele extends Observable {
    /**
     * La grille du jeu.
     */
    private final Grille grille;

    /**
     * L'ensemble de joueurs.
     */
    private final List<Joueur> ensemble;

    /**
     * Un itérateur de l'ensemble de joueurs.
     */
    private Iterator<Joueur> iter;

    /**
     * Le nombre de tours parcourus.
     */
    private int tour;

    /**
     * Le joueur actuel.
     */
    private Joueur joueurActuel;

    /**
     * Construit un jeu à partir d'une carte et l'ensemble d'objets et de joueurs.
     * @param carte Une carte
     * @param jeu L'ensemble d'objets et de joueurs.
     */
    public Modele(Carte carte, Jeu jeu) {
        this.grille = new Grille(carte);
        this.grille.addObjets(jeu.objets);
        this.ensemble = new ArrayList<>();
        for (AbstractMap.SimpleImmutableEntry<Joueur, Coord> p: jeu.ensemble) {
            Joueur j = p.getKey();
            Coord c = p.getValue();
            j.teleport(this.grille.getCase(c));
            this.ensemble.add(j);
        }
        this.tour = 1;
        this.joueurActuel = this.ensemble.get(0);
        this.iter = this.ensemble.iterator();
    }

    /**
     * Construit un jeu à partir d'un fichier texte et d'un fichier XML.
     * @param map_path Un fichier texte
     * @param game_path Un fichier XML
     */
    public Modele (String map_path, String game_path) throws InvalidGameException {
        this(new Carte(map_path), new Jeu(game_path));
    }

    /**
     * Renvoie le joueur actuel.
     * @return Le joueur actuel.
     */
    public Joueur getJoueurActuel() { return this.joueurActuel; }

    /**
     * Renvoie le nombre de joueurs au total.
     * @return Renvoie le nombre de joueurs au total.
     */
    public int getNbJoueurs(){
        return this.ensemble.size();
    }

    /**
     * Incrémente au prochain tour.
     */
    public void tourSuivant() {
        this.tour++;
        this.grille.inonde();
        for (Joueur joueur: this.ensemble) { joueur.noie(); }
        this.joueurActuel = this.prochainJoueurVivant();
        this.joueurActuel.newTurn();
    }

    /**
     * Renvoie le tour actuel.
     * @return Le tour actuel.
     */
    public int getTour() { return this.tour; }

    /**
     * Renvoie la grille.
     * @return La grille.
     */
    public Grille getGrille(){ return this.grille; }

    /**
     * Vérifie qui est le joueur gagnant.
     * @return le joueur gagnant, null s'il n'y a pas.
     */
    public Joueur verifieGagnants() {
        for (Joueur j: this.ensemble) {
            if (j.verifieGagnant()) {
                return j;
            }
        }
        return null;
    }

    /**
     * Vérifie si le tour actuel peut se terminer.
     * @return Vrai si oui, Faux sinon.
     */
    public boolean tourPeutFinir() {
        return this.joueurActuel.surCaseTraversable();
    }

    /**
     * Calcule le joueur prochain vivant.
     * @return le joueur prochain vivant.
     */
    private Joueur prochainJoueurVivant() {
        if (!this.iter.hasNext()) {
            this.iter = this.ensemble.iterator();
        }
        Joueur joueur = this.iter.next();
        while (!joueur.estVivant()) {
            joueur = this.iter.next();
            if (!this.iter.hasNext()) {
                this.iter = this.ensemble.iterator();
            }
        }
        return joueur;
    }

    /**
     * Vérifie si tous les joueurs sont morts.
     * @return Vrai si tous les joueurs sont morts, Faux sinon.
     */
    public boolean tousJoueursMorts(){
        return this.ensemble.stream().noneMatch(j -> j.estVivant());
    }
}