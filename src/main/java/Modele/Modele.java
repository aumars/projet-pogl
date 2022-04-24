package Modele;

import Modele.Exception.InvalidGameException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Le système de jeu.
 */
public class Modele extends Observable {
    /**
     * La carte du jeu.
     */
    private final Carte CARTE;

    /**
     * Le jeu.
     */
    private final Jeu JEU;

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
     * La difficulté du jeu
     */
    private Difficulte difficulte;

    /**
     * Construit un jeu à partir d'un fichier texte et d'un fichier XML.
     * @param map_path Un fichier texte
     * @param game_path Un fichier XML
     * @param difficulte La difficulté du jeu.
     */
    public Modele (String map_path, String game_path, Difficulte difficulte) throws InvalidGameException {
        this.CARTE = new Carte(map_path);
        this.JEU = new Jeu(game_path);
        this.difficulte = difficulte;
        double probaClefInondation;
        if (this.difficulte == Difficulte.DETERMINISTE) {
            probaClefInondation = 0;
        }
        else {
            probaClefInondation = 0.2;
        }
        this.getGrille().addObjets(JEU.OBJETS);
        for (Map.Entry<Joueur, Coord> p: JEU.ENSEMBLE) {
            Joueur j = p.getKey();
            Coord c = p.getValue();
            j.setPosInitiale(this.getGrille().getCase(c));
            j.setProbaClefInondation(probaClefInondation);
        }
        this.getJoueurs().forEach(j -> j.restart());
        this.getJoueurs().forEach(j -> this.getGrille().getCase(j.getCoord()).setJoueur(j));
        this.iter = this.getJoueurs().iterator();
        this.finJeu = false;
        this.commenceTour();
    }

    /**
     * Rétablit les attributs initiaux du modèle.
     */
    public void restart() {
        this.getGrille().restart();
        this.getJoueurs().forEach(j -> j.restart());
        this.getJoueurs().forEach(j -> this.getGrille().getCase(j.getCoord()).setJoueur(j));
        this.tour = 1;
        this.iter = this.getJoueurs().iterator();
        this.joueurActuel = this.iter.next();
        this.finJeu = false;
    }

    /**
     * Modifie difficulté du jeu.
     * @param difficulte Nouvelle difficulté
     */
    public void setDifficulte(Difficulte difficulte) { this.difficulte = difficulte; }

    /**
     * @return Vrai si le jeu est déterministe, faux sinon.
     */
    private boolean estDeterministe() { return this.difficulte == Difficulte.DETERMINISTE; }

    public List<Joueur> getJoueurs(){
        return this.JEU.ENSEMBLE.stream().map(AbstractMap.SimpleImmutableEntry::getKey).collect(Collectors.toList());
    }

    /**
     * @return Le joueur actuel.
     */
    public Joueur getJoueurActuel() { return this.joueurActuel; }

    /**
     * @return Renvoie le nombre de joueurs au total.
     */
    public int getNbJoueurs(){
        return this.getJoueurs().size();
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
                this.getGrille().inonde();
            }
            this.getJoueurs().forEach(j -> j.noie());
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
        System.out.printf("--- Tour %d | Joueur %d ---%n", this.getTour(), this.getJoueurActuel().ID);
    }

    /**
     * @return Le tour actuel.
     */
    public int getTour() { return this.tour; }

    /**
     * @return La grille.
     */
    public Grille getGrille(){ return this.CARTE.GRILLE; }

    /**
     * @return Vrai si est le jeu est gagné, Faux sinon.
     */
    public boolean verifieGagnants() {
        return this.tousJoueursSurHelipad() && this.tousArtefactsRecuperes();
    }

    /**
     * @return Vrai si tous les joueurs sont sur le helipad, Faux sinon.
     */
    private boolean tousJoueursSurHelipad() {
        return this.getJoueurs().stream().allMatch(j -> j.surHelipad());
    }

    /**
     * @return Vrai si tous les artefacts sont récupérés, Faux sinon.
     */
    private boolean tousArtefactsRecuperes() {
        Set<Artefact> a = new HashSet<>();
        for (Joueur j: this.getJoueurs()) {
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
     * @return Vrai si le tour actuel peut se terminer, Faux sinon.
     */
    public boolean tourPeutFinir() {
        return this.joueurActuel.surCaseTraversable();
    }

    /**
     * @return Le joueur prochain vivant.
     */
    private Joueur prochainJoueurVivant() {
        if (!this.iter.hasNext()) {
            this.tour++;
            this.iter = this.getJoueurs().iterator();
        }
        Joueur joueur = this.iter.next();
        while (!joueur.estVivant()) {
            if (!this.iter.hasNext()) {
                this.tour++;
                this.iter = this.getJoueurs().iterator();
            }
            else {
                joueur = this.iter.next();
            }
        }
        return joueur;
    }

    /**
     * @return Vrai si tous les joueurs sont morts, Faux sinon.
     */
    public boolean tousJoueursMorts() {
        if (this.getJoueurs().stream().noneMatch(j -> j.estVivant())) {
            this.finJeu = true;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return Vrai si le jeu est terminé, Faux sinon.
     */
    public boolean getFinJeu() { return this.finJeu; }
}