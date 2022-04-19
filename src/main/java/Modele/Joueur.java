package Modele;

import java.util.*;

/**
 * Joueur, entité dirigé par un être humain.
 */
public class Joueur {
    /**
     * Si le Joueur vive ou pas.
     */
    private boolean vivant;

    /**
     * La case actuelle du Joueur.
     */
    private Case pos;

    /**
     * La case initiale du Joueur.
     */
    private Case posInitiale;

    /**
     * L'inventaire d'objets en possession du Joueur.
     */
    private final List<Objet> inventaire;

    /**
     * Si le Joueur peut actuellement jouer son tour.
     */
    private boolean sonTour;

    /**
     * Le nombre de Joueurs instanciés.
     */
    private static int numJoueurs = 0;

    /**
     * Le numéro d'identification du Joueur.
     */
    public final int id;

    /**
     * Si le Joueur peut faire une action spéciale.
     */
    private boolean actionSpeciale = false;

    /**
     * Le nombre de cases survecues consécutives.
     */
    private int casesSurvecuesConsecutives = 0;

    /**
     * Probabilité (entre 0 et 1) d'inonder la case actuelle lors d'une recherche d'une clef.
     */
    private double probaClefInondation;

    /**
     * La phrase de log actuel.
     */
    private String log;

    /**
     * Construit un Joueur sans position. Il faut donc préciser sa position plus tard avec teleport().
     */
    public Joueur() {
        this(null);
    }

    /**
     * Construit un Joueur avec position.
     * @param c Position du Joueur.
     */
    public Joueur(Case c) {
        this.posInitiale = c;
        this.inventaire = new ArrayList<>();
        this.id = numJoueurs;
        numJoueurs++;
        this.restart();
    }

    /**
     * Rétablit les attributs initiaux du Joueur.
     */
    public void restart() {
        this.vivant = true;
        this.teleport(this.posInitiale);
        this.inventaire.clear();
        this.newTurn();
    }

    @Override
    public String toString() {
        return String.format("Joueur %d", this.id);
    }

    /**
     * Afficher un message qui décrit l'action du Joueur.
     * @param msg Une action du Joueur (ex : "se déplace").
     */
    private void log(String msg) {
        if (this.pos != null) {
            this.log = String.format("%s: %s %s%n", this.pos, this, msg);
        }
        else {
            this.log = String.format("%s %s%n", this, msg);
        }

        System.out.printf(this.log);
    }

    /**
     * Modifier la proba d'inonder une case en cherchant une clef.
     * @param p La probabilité (entre 0 et 1)
     */
    public void setProbaClefInondation(double p) { this.probaClefInondation = p; }

    /**
     * Le Joueur meurt.
     */
    public void meurt() {
        this.log("est mort !");
        this.vivant = false;
    }

    /**
     * Le Joueur revient en vie.
     */
    public void revive() {
        this.log("est revenu !");
        this.vivant = true;
    }

    /**
     * Noie le Joueur si sa case et ses adjacentes sont submergées.
     */
    public void noie() {
        if (this.pos.adjacentSubmergee() && this.estVivant()) {
            this.meurt();
        }
    }

    /**
     * Renvoie le status du Joueur.
     * @return Vrai si le Joueur vive, Faux sinon.
     */
    public boolean estVivant() { return this.vivant; }

    /**
     * Renvoie le tour du Joueur.
     * @return Le tour du Joueur.
     */
    public boolean estSonTour() { return this.sonTour; }

    /**
     * Termine le tour du Joueur.
     */
    public void finishTurn() { this.sonTour = false; }

    /**
     * Renouvelle le tour du Joueur.
     */
    public void newTurn() { this.sonTour = true; }

    /**
     * On gagne une action spéciale si :
     * - on trouve une clef pour la première fois
     * - on survit 10 cases
     */
    public void gagneActionSpeciale() {
        this.actionSpeciale = true;
        this.log("gagne une action spéciale !");
    }

    /**
     * Vérifie si le Joueur peut faire une action spéciale.
     */
    public boolean aActionSpeciale() {
        return this.actionSpeciale;
    }

    /**
     * Déplace le Joueur à une case adjacente.
     * @param dir Direction du déplacement.
     * @return Vrai le déplacement est réussi, faux sinon.
     */
    public boolean deplace(Direction dir) {
        Case adjacent = this.pos.adjacent(dir);
        if (this.estSonTour() && this.pos.adjacent(dir).estTraversable()) {
            if (!this.pos.estTraversable()) {
                this.casesSurvecuesConsecutives = 0;
            }
            else {
                this.casesSurvecuesConsecutives++;
                if (this.casesSurvecuesConsecutives == 10) {
                    this.gagneActionSpeciale();
                    this.casesSurvecuesConsecutives = 0;
                }
            }
            this.log(String.format("se déplace vers %s à %s", dir, adjacent));
            this.pos.removeJoueur(this);
            this.pos = this.pos.adjacent(dir);
            this.pos.setJoueur(this);
            this.finishTurn();
            return true;
        }
        else {
            this.log(String.format("ne peut pas se déplacer vers %s à %s", dir, adjacent));
            return false;
        }
    }

    /**
     * Vérifie si le Joueur possède la clef d'un élément spécifié.
     * @param el Element.
     * @return Vrai si le Joueur la possède, Faux sinon.
     */
    public boolean possedeClef(Element el) {
        return this.inventaire.stream().anyMatch(o -> o instanceof Clef && o.element == el);
    }

    /**
     * Prend l'objet de la case du Joueur.
     */
    public Objet prendObjet() {
        if (this.pos.aObjet()) {
            Objet objet = this.pos.getObjet();
            this.inventaire.add(objet);
            this.log(String.format("prend %s", objet));
            return objet;
        }
        else {
            this.log("ne prend pas d'objet");
            return null;
        }
    }

    /**
     * Renvoie la liste d'{@link Objet}s en possession du Joueur.
     * @return La liste d'{@link Objet}s en possession du Joueur.
     */
    public List<Objet> getInventaire(){
        return this.inventaire;
    }

    /**
     * Renvoie les coordonnées du Joueur.
     * @return Les coordonnées du Joueur.
     */
    public Coord getCoord() { return this.pos.coord; }

    /**
     * Assèche une case adjacente au Joueur.
     * @param dir Direction que le Joueur souhaite assécher.
     */
    public void asseche(Direction dir) {
        Case adjacent = this.pos.adjacent(dir);
        if (this.estSonTour() && adjacent.asseche()) {
            this.log(String.format("assèche %s", adjacent));
            this.finishTurn();
        }
    }

    /**
     * Action spéciale. Assèche n'importe quelle case assèchable.
     * @param c Une case.
     */
    public void asseche(Case c) {
        if (this.estSonTour() && c.asseche() && this.aActionSpeciale()) {
            this.log(String.format("assèche %s (action spéciale)", c));
            this.actionSpeciale = false;
            this.finishTurn();
        }
    }

    /**
     * Récupère l'Artefact sur la case du Joueur, s'il a la clef correspondante.
     */
    public Objet recupereArtefact() {
        if (this.estSonTour() && this.pos.aObjet(Artefact.class) && this.possedeClef(this.pos.getObjet().element)) {
            Objet artefact = this.prendObjet();
            this.finishTurn();
            return artefact;
        }
        else {
            this.log("ne prend pas d'artefact");
            return null;
        }
    }

    /**
     * Cherche une Clef dans la case du Joueur.
     * @return la clef si le joueur la récupére sinon null. 
     */
    public Objet chercheCle() {
        this.finishTurn();

        if (this.pos.aObjet(Clef.class)) {
            if (!this.pos.getObjetVisibilite()) {
                this.gagneActionSpeciale();
            }
            this.pos.setObjetVisibilite(true);
            return this.prendObjet();
        }
        else {
            double dice = new Random().nextDouble();
            if (dice < this.probaClefInondation) {
                this.pos.monteEaux();
                this.log(String.format("n'a pas trouvé de clef et sa case inonde ! (proba de %.1f)", this.probaClefInondation));
            }
            else {
                this.log("n'a pas trouvé de clef");
            }
            return null;
        }
    }

    /**
     * Met à jour la position du Joueur dans une case de la grille.
     * @param c Une case de la grille.
     */
    public void teleport(Case c) {
        this.log(String.format("téléporte vers %s", c));
        this.pos = c;
    }

    /**
     * Action spéciale. Se transporte vers n'importe quelle case.
     * @param c Une case.
     */
    public void helicoptere(Case c) {
        if (this.aActionSpeciale() && c.estTraversable()) {
            this.log(String.format("prend un hélicoptère vers %s (action spéciale)", c));
            this.actionSpeciale = false;
            this.pos.removeJoueur(this);
            this.pos = c;
            this.pos.setJoueur(this);
        }
    }

    /**
     * Initialise la position initiale.
     * @param c Une Case.
     */
    public void setPosInitiale(Case c) {
        if (this.posInitiale == null && c != null) {
            this.posInitiale = c;
            this.log(String.format("a sa position initiale à %s", c));
        }
    }

    /**
     * Vérifie si le Joueur est sur une case traversable.
     * @return Vrai si le Joueur est sur une case traversable, Faux sinon.
     */
    public boolean surCaseTraversable() {
        return this.pos.estTraversable();
    }

    /**
     * Vérifie si le Joueur est sur un helipad.
     */
    public boolean surHelipad() {
        return this.pos.estHelipad();
    }

    public String getLogString(){
        return this.log;
    }
}

