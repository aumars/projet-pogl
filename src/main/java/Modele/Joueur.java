package Modele;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Joueur {
    private boolean vivant = true;
    private Case pos;
    private final Set<Objet> inventaire;
    private boolean endTurn;

    public Joueur() {
        this(null);
    }

    public Joueur(Case c) {
        this.pos = c;
        inventaire = new HashSet<>();
        this.endTurn = false;
    }

    public static Joueur joueurById(String name, Case c) {
        if (name.equals("joueur")) {
            return new Joueur(c);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void noie() {
        if (this.pos.adjacentSubmergee()) {
            this.vivant = false;
        }
    }

    public boolean estVivant() { return this.vivant; }

    public boolean estSonTour() { return this.sonTour; }

    public void finishTurn() { this.endTurn = true; }

    public void newTurn() { this.endTurn = false; }

    public boolean deplace(Direction dir) {
        if (!this.getEndTurn() && this.pos.adjacent(dir).estTraversable()) {
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

    public void prendObjet(Objet o) {
        this.inventaire.add(o);
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
        if (!this.getEndTurn() && this.pos.adjacent(dir).asseche()) {
            this.finishTurn();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean recupereArtefact() {
        if (!this.getEndTurn() && this.pos.aObjet(Artefact.class) && this.possedeClef(this.pos.getObjet().element)) {
            this.prendObjet(this.pos.getObjet());
            this.finishTurn();
            return true;
        }
        return false;
    }

    public boolean chercheCle() {
        if (this.pos.aObjet(Clef.class)) {
            this.prendObjet(this.pos.getObjet());
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
