package Modele;

import java.util.*;

/**
 * Joueur, entité dirigé par un être humain.
 */
public class Joueur {
    /**
     * Si le Joueur vive ou pas.
     */
    private boolean vivant = true;

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
     * Le nom du joueur.
     */
    public final String nom = "Player";

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
        this.id = numJoueurs + 1;
        numJoueurs++;
        this.restart();
    }

    /**
     * Rétablit les attributs initiaux du Joueur.
     */
    public void restart() {
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
            System.out.printf("%s: %s %s%n", this.pos, this, msg);
        }
        else {
            System.out.printf("%s %s%n", this, msg);
        }
    }

    /**
     * Noie le Joueur si sa case et ses adjacentes sont submergées.
     */
    public void noie() {
        if (this.pos.adjacentSubmergee() && this.estVivant()) {
            this.log("est mort !");
            this.vivant = false;
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
            Case adjacent = this.pos.adjacent(dir);
            this.log(String.format("se déplace vers %s à %s", dir, adjacent));
            this.pos = this.pos.adjacent(dir);
            this.finishTurn();
            return true;
        }
        else {
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
            if (dice < 0.2) {
                this.pos.monteEaux();
                this.log("n'a pas réussi de chercher une clef et sa case inonde !");
            }
            else {
                this.log("n'a pas réussi de chercher une clef");
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
}

