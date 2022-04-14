package Modele;

import java.util.*;

/**
 * Joueur, entité dirigé par un être humain.
 */
public class Joueur {
    private boolean vivant = true;
    private Case pos;
    private final List<Objet> inventaire;
    private boolean sonTour;
    public String nom = "Player";

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
        this.pos = c;
        this.inventaire = new ArrayList<>();
        this.sonTour = true;
    }

    /**
     * Noie le Joueur si sa case et ses adjacentes sont submergées.
     */
    public void noie() {
        if (this.pos.adjacentSubmergee()) {
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
     * Déplace le Joueur à une case adjacente.
     * @param dir Direction du déplacement.
     * @return Vrai le déplacement est réussi, faux sinon.
     */
    public boolean deplace(Direction dir) {
        if (this.estSonTour() && this.pos.adjacent(dir).estTraversable()) {
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
            this.inventaire.add(this.pos.getObjet());
            this.pos.detruitObjet();
            return objet;
        }

        return null;
    }

    public List<Objet> getInventaire(){
        return this.inventaire;
    }

    /**
     * Verifie que le Joueur possède tous les artefacts.
     * @return Vrai si le Joueur possède tous les artefacts, Faux sinon.
     */
    public boolean possedeTousArtefacts() {
        for (Element el: Element.values()) {
            if (this.inventaire.stream().noneMatch(o -> o instanceof Artefact && o.element == el)) {
                return false;
            }
        }
        return true;
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
        if (this.estSonTour() && this.pos.adjacent(dir).asseche()) {
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
        return null;
    }

    /**
     * Cherche une Clef dans la case du Joueur.
     * @return la clef si le joueur la récupére sinon null. 
     */
    public Objet chercheCle() {
        this.finishTurn();
        
        if (this.pos.aObjet(Clef.class)) {
            this.pos.setObjetVisibilite(true);
            return this.prendObjet();
        }
        else {
            double dice = new Random().nextDouble();
            if (dice < 0.2) {
                this.pos.monteEaux();
            }
            return null;
        }
    }

    /**
     * Vérifie si le Joueur a gagné.
     * @return Vrai si la Joueur a gagné, Faux sinon.
     */
    public boolean verifieGagnant() {
        return this.pos.estHelipad() && this.possedeTousArtefacts();
    }

    /**
     * Met à jour la position du Joueur dans une case de la grille.
     * @param c Une case de la grille.
     */
    public void teleport(Case c) { this.pos = c; }
}

