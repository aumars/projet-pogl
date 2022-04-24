package Modele;

import java.util.*;

/**
 * Joueur, entité dirigé par un être humain.
 */
public class Joueur {
    /**
     * Les noms des Joueurs.
     */
    private final static String[] AVATARS = { "Jack", "Guy", "Ninja", "Pinky" };

    /**
     * Le nombre de Joueurs instanciés.
     */
    private static int numJoueurs = 0;

    /**
     * Le numéro d'identification du Joueur.
     */
    public final int ID;

    /**
     * L'inventaire d'objets en possession du Joueur.
     */
    private final List<Objet> INVENTAIRE;

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
     * Si le Joueur peut actuellement jouer son tour.
     */
    private boolean sonTour;

    /**
     * Si le Joueur peut faire une action spéciale.
     */
    private boolean actionSpeciale = false;

    /**
     * Le nombre de cases survecues consécutives.
     */
    private int casesSurvecuesConsecutives = 0;

    /**
     * Probabilité (entre 0 et 1) d'inonder la case actuelle lors d'une recherche
     * d'une clef.
     */
    private double probaClefInondation;

    /**
     * La phrase de log actuel.
     */
    private String log;

    /**
     * Le nombre d'actions disponible
     */
    private int actions;

    /**
     * Construit un Joueur sans position. Il faut donc préciser sa position plus
     * tard avec teleport().
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
        this.INVENTAIRE = new ArrayList<>();
        this.ID = numJoueurs;
        numJoueurs++;
        this.restart();
    }

    /**
     * Rétablit les attributs initiaux du Joueur.
     */
    public void restart() {
        this.vivant = true;
        this.teleport(this.posInitiale);
        this.INVENTAIRE.clear();
        this.newTurn();
    }

    /**
     * Verifie si le joueurs a zero action
     * @return True si le joueur n'as plus d'action. False sinon.
     */
    public boolean aZeroAction(){
        return actions == 0;
    }

    /**
     * Renvoie le nom du Joueur.
     * @return Une chaine de caractères du nom du Joueur
     */
    @Override
    public String toString() {
        //return String.format("%s (%d)", avatars[this.id % 4], this.id);
        return AVATARS[this.ID % 4];
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

        System.out.print(this.log);
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
    public void newTurn() {
        this.actions = 3;
        this.sonTour = true;
    }

    /**
     * Renvoie le nombre d'actions disponible
     */
    public int nbActions() { return this.actions; }

    /**
     * Renvoie si on peut jouer une action
     */
    public boolean peutFaireAction() { return this.actions > 0 && this.estSonTour(); }

    /**
     * Terminer l'action
     */
    private void finishAction() {
        this.actions--;
    }

    /**
     * On gagne une action spéciale si :
     * - on trouve une clef pour la première fois
     * - on survit 10 cases
     */
    private void gagneActionSpeciale() {
        this.actionSpeciale = true;
        this.log("gagne une action spéciale !");
    }

    /**
     * Finir l'action spéciale
     */
    private void finishActionSpeciale() {
        this.actionSpeciale = false;
    }

    /**
     * Vérifie si le Joueur peut faire une action spéciale.
     */
    public boolean aActionSpeciale() {
        return this.estSonTour() && this.actionSpeciale;
    }

    /**
     * Déplace le Joueur à une case adjacente.
     * @param dir Direction du déplacement.
     * @return Vrai le déplacement est réussi, faux sinon.
     */
    public boolean deplace(Direction dir) {
        Case adjacent = this.pos.adjacent(dir);
        if (this.peutFaireAction() && this.pos.adjacent(dir).estTraversable()) {
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
            this.finishAction();
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
        return this.INVENTAIRE.stream().anyMatch(o -> o instanceof Clef && o.ELEMENT == el);
    }

    /**
     * Prend l'objet de la case du Joueur.
     */
    private Objet prendObjet() {
        if (this.pos.aObjet()) {
            Objet objet = this.pos.getObjet();
            this.INVENTAIRE.add(objet);
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
    public List<Objet> getInventaire() {
        return this.INVENTAIRE;
    }

    /**
     * Renvoie les coordonnées du Joueur.
     * @return Les coordonnées du Joueur.
     */
    public Coord getCoord() {
        return this.pos.COORD;
    }

    /**
     * Assèche une case adjacente au Joueur.
     * @param dir Direction que le Joueur souhaite assécher.
     */
    public void asseche(Direction dir) {
        Case adjacent = this.pos.adjacent(dir);
        if (this.peutFaireAction() && adjacent.asseche()) {
            this.log(String.format("assèche %s", adjacent));
            this.finishAction();
        }
    }

    /**
     * Action spéciale. Assèche n'importe quelle case assèchable.
     * @param c Une case.
     */
    public void asseche(Case c) {
        if (this.aActionSpeciale()) {
            this.log(String.format("assèche %s (action spéciale)", c));
            this.finishActionSpeciale();
            this.finishTurn();
        }
    }

    /**
     * Récupère l'Artefact sur la case du Joueur, s'il a la clef correspondante.
     */
    public Objet recupereArtefact() {
        if (this.estSonTour() && this.pos.aObjet(Artefact.class) && this.possedeClef(this.pos.getObjet().ELEMENT)) {
            Objet artefact = this.prendObjet();
            this.finishAction();
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
            this.finishActionSpeciale();
            this.pos.removeJoueur(this);
            this.pos = c;
            this.pos.setJoueur(this);
            this.finishTurn();
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

    /**
     * Renvoie une chaine de caractère du log actuel.
     */
    public String getLogString() {
        return this.log;
    }
}
