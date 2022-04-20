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
     * Marqueur pour la fin du jeu.
     */
    private boolean finJeu;

    /**
     * La proba d'inonder une case en recherche d'une clef.
     */
    private final double probaClefInondation;

    private final Difficulte difficulte;

    /**
     * Construit un jeu à partir d'une carte et l'ensemble d'objets et de joueurs.
     * @param carte Une carte
     * @param jeu L'ensemble d'objets et de joueurs.
     */
    public Modele(Carte carte, Jeu jeu, Difficulte difficulte) {
        this.difficulte = difficulte;
        if (this.difficulte == Difficulte.DETERMINISTE) {
            this.probaClefInondation = 0;
        }
        else {
            this.probaClefInondation = 0.2;
        }
        this.grille = new Grille(carte);
        this.grille.addObjets(jeu.objets);
        this.ensemble = new ArrayList<>();
        for (AbstractMap.SimpleImmutableEntry<Joueur, Coord> p: jeu.ensemble) {
            Joueur j = p.getKey();
            Coord c = p.getValue();
            j.setPosInitiale(this.grille.getCase(c));
            j.setProbaClefInondation(this.probaClefInondation);
            this.ensemble.add(j);
        }
        this.ensemble.forEach(j -> j.restart());
        this.ensemble.forEach(j -> this.grille.getCase(j.getCoord()).setJoueur(j));
        this.iter = this.ensemble.iterator();
        this.finJeu = false;
        this.commenceTour();
    }

    /**
     * Construit un jeu à partir d'un fichier texte et d'un fichier XML.
     * @param map_path Un fichier texte
     * @param game_path Un fichier XML
     */
    public Modele (String map_path, String game_path, Difficulte difficulte) throws InvalidGameException {
        this(new Carte(map_path), new Jeu(game_path), difficulte);
    }

    /**
     * Rétablit les attributs initiaux du modèle.
     */
    public void restart() {
        this.grille.restart();
        this.ensemble.forEach(j -> j.restart());
        this.ensemble.forEach(j -> this.grille.getCase(j.getCoord()).setJoueur(j));
        this.tour = 1;
        this.iter = this.ensemble.iterator();
        this.joueurActuel = this.iter.next();
        this.finJeu = false;
    }

    private boolean estDeterministe() { return this.difficulte == Difficulte.DETERMINISTE; }

    public List<Joueur> getJoueurs(){
        return this.ensemble;
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
        if (this.tourPeutFinir()) {
            switch (this.difficulte) {
                case DIFFICILE: this.getGrille().inonde();
                case MOYEN: this.getGrille().inonde();
                case FACILE: this.getGrille().inonde();
                default:
            }
            if (!this.estDeterministe()) {
                this.grille.inonde();
            }
            this.ensemble.forEach(j -> j.noie());
            if (!this.tousJoueursMorts()) {
                this.commenceTour();
            }
            else {
                this.finJeu = true;
            }
        }
    }

    /**
     * Initialise un Tour.
     */
    private void commenceTour() {
        this.joueurActuel = this.prochainJoueurVivant();
        this.joueurActuel.newTurn();
        System.out.printf("--- Tour %d | Joueur %d ---%n", this.getTour(), this.getJoueurActuel().id);
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
     * Vérifie si est le jeu est gagné.
     */
    public boolean verifieGagnants() {
        return this.tousJoueursSurHelipad() && this.tousArtefactsRecuperes();
    }

    /**
     * Vérifie si tous les joueurs sont sur le helipad.
     */
    private boolean tousJoueursSurHelipad() {
        return this.ensemble.stream().allMatch(j -> j.surHelipad());
    }

    /**
     * Vérifie si tous les artefacts sont récupérés.
     */
    private boolean tousArtefactsRecuperes() {
        Set<Artefact> a = new HashSet<>();
        for (Joueur j: this.ensemble) {
            List<Objet> objets = j.getInventaire();
            for (Objet o: objets) {
                if (o instanceof Artefact) {
                    a.add((Artefact) o);
                }
            }
        }
        return a.size() == 4;
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
            this.tour++;
            this.iter = this.ensemble.iterator();
        }
        Joueur joueur = this.iter.next();
        while (!joueur.estVivant()) {
            if (!this.iter.hasNext()) {
                this.tour++;
                this.iter = this.ensemble.iterator();
            }
            else {
                joueur = this.iter.next();
            }
        }
        return joueur;
    }

    /**
     * Vérifie si tous les joueurs sont morts.
     * @return Vrai si tous les joueurs sont morts, Faux sinon.
     */
    public boolean tousJoueursMorts(){
        if (this.ensemble.stream().noneMatch(j -> j.estVivant())) {
            this.finJeu = true;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Vérifie si le jeu est terminé.
     * @return Vrai si le jeu est terminé, Faux sinon.
     */
    public boolean getFinJeu() { return this.finJeu; }
}