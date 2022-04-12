package Modele;

import Modele.Exception.InvalidGameException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Modele extends Observable {
    private final Grille grille;
    private final List<Joueur> ensemble;
    private Iterator<Joueur> iter;
    private int tour;
    private Joueur joueurActuel;

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

    public Modele (String map_path, String game_path) throws ParserConfigurationException, IOException, SAXException, InvalidGameException {
        this(new Carte(map_path), new Jeu(game_path));
    }

    public Joueur getJoueurActuel() { return this.joueurActuel; }

    public boolean tourSuivant() {
        this.tour++;
        this.grille.inonde();
        for (Joueur joueur: this.ensemble) { joueur.noie(); }
        if (this.ensemble.stream().noneMatch(joueur -> joueur.estVivant())) {
            return false;
        }
        this.joueurActuel = this.prochainJoueurVivant();
        this.joueurActuel.newTurn();
        return true;
    }

    public int getTour() { return this.tour; }

    public Grille getGrille(){ return this.grille; }

    public Joueur verifieGagnants() {
        for (Joueur j: this.ensemble) {
            if (j.verifieGagnant()) {
                return j;
            }
        }
        return null;
    }

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
}