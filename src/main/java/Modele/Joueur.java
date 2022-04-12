package Modele;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Joueur {
    private boolean vivant = true;
    private Case pos;
    private final Set<Objet> inventaire;
    private boolean sonTour;

    public Joueur() {
        this(null);
    }

    public Joueur(Case c) {
        this.pos = c;
        this.inventaire = new HashSet<>();
        this.sonTour = true;
    }

    public void noie() {
        if (this.pos.adjacentSubmergee()) {
            this.vivant = false;
        }
    }

    public boolean estVivant() { return this.vivant; }

    public boolean estSonTour() { return this.sonTour; }

    public void finishTurn() { this.sonTour = false; }

    public void newTurn() { this.sonTour = true; }

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

    public boolean possedeClef(Element el) {
        return this.inventaire.stream().anyMatch(o -> o instanceof Clef && o.element == el);
    }

    /**
     * Prend l'objet de la case du Joueur.
     * @return Vrai si la case a un objet, Faux sinon.
     */
    public boolean prendObjet() {
        if (this.pos.aObjet()) {
            this.pos.detruitObjet();
            this.inventaire.add(this.pos.getObjet());
            return true;
        }
        else {
            return false;
        }
    }

    public boolean possedeTousArtefacts() {
        for (Element el: Element.values()) {
            if (this.inventaire.stream().noneMatch(o -> o instanceof Artefact && o.element == el)) {
                return false;
            }
        }
        return true;
    }

    public Coord getCoord() { return this.pos.coord; }

    public boolean asseche(Direction dir) {
        if (this.estSonTour() && this.pos.adjacent(dir).asseche()) {
            this.finishTurn();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean recupereArtefact() {
        if (this.estSonTour() && this.pos.aObjet(Artefact.class) && this.possedeClef(this.pos.getObjet().element)) {
            this.prendObjet();
            this.finishTurn();
            return true;
        }
        return false;
    }

    public boolean chercheCle() {
        if (this.pos.aObjet(Clef.class)) {
            this.prendObjet();
            return true;
        }
        else {
            double dice = new Random().nextDouble();
            if (dice < 0.2) {
                this.pos.monteEaux();
            }
            return false;
        }
    }

    public boolean verifieGagnant() {
        return this.pos.helipad && this.possedeTousArtefacts();
    }

    public void teleport(Case c) { this.pos = c; }
}
