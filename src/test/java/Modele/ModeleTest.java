package Modele;

import Modele.Exception.InvalidGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModeleTest {
    Modele modeleM1G1, modeleM1G2;
    @BeforeEach
    void modeleInit() throws InvalidGameException {
        this.modeleM1G1 = new Modele("map1.txt", "game1.xml");
        this.modeleM1G2 = new Modele("map1.txt", "game2.xml");
    }

    @Test
    void modeleJoueurPosition() {
        Assertions.assertEquals(new Coord(1, 1), this.modeleM1G1.getJoueurActuel().getCoord());
    }

    @Test
    void modeleJoueurInvalidDirectionGame1() {
        Assertions.assertFalse(this.modeleM1G1.getJoueurActuel().deplace(Direction.HAUT));
    }

    @Test
    void modeleJoueurValidDirectionGame1() {
        Assertions.assertTrue(this.modeleM1G1.getJoueurActuel().deplace(Direction.BAS));
        Assertions.assertEquals(new Coord(1, 2), this.modeleM1G1.getJoueurActuel().getCoord());
    }

    @Test
    void modeleGetNbJoueursGame1() {
        Assertions.assertEquals(4, this.modeleM1G1.getNbJoueurs());
    }

    @Test
    void modeleGetNbJoueursGame2() {
        Assertions.assertEquals(1, this.modeleM1G2.getNbJoueurs());
    }

    @Test
    void modeleGetJoueurActuelGame1() {
        Modele m = this.modeleM1G1;
        Joueur j1 = m.getJoueurActuel();
        m.tourSuivant();
        Joueur j2 = m.getJoueurActuel();
        m.tourSuivant();
        Joueur j3 = m.getJoueurActuel();
        m.tourSuivant();
        Joueur j4 = m.getJoueurActuel();
        m.tourSuivant();
        Joueur j5 = m.getJoueurActuel();
        Assertions.assertNotSame(j1, j2);
        Assertions.assertNotSame(j1, j3);
        Assertions.assertNotSame(j1, j4);
        Assertions.assertSame(j1, j5);
    }

    @Test
    void modeleTestTourSuivantGame1() {
        Modele m = this.modeleM1G1;
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
    }

    @Test
    void modeleTestTourSuivantGame2() {
        Modele m = this.modeleM1G2;
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(3, 1), m.getJoueurActuel().getCoord());
    }

    @Test
    void modeleTestGame1() {
        Modele m = this.modeleM1G1;
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertEquals(new Coord(2, 1), m.getJoueurActuel().getCoord());
    }

    @Test
    void modeleTestGame2() {
        Modele m = this.modeleM1G2;
        m.getJoueurActuel().deplace(Direction.DROITE);
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.DROITE);
        m.tourSuivant();
        m.getJoueurActuel().deplace(Direction.BAS);
        Assertions.assertNotNull(m.getJoueurActuel().chercheCle());
    }

    @Test
    void modeleCasesAdjacentesInondeesGame2() {
        Modele m = this.modeleM1G2;
        Case c1 = m.getGrille().getCase(new Coord(2, 1));
        Case c2 = m.getGrille().getCase(new Coord(1, 2));
        c1.monteEaux();
        c1.monteEaux();
        c2.monteEaux();
        c2.monteEaux();
        m.tourSuivant();
        Assertions.assertTrue(m.tousJoueursMorts());
    }

    @Test
    void modeleCaseActuelleInondeeGame2() {
        Modele m = this.modeleM1G2;
        Case c1 = m.getGrille().getCase(m.getJoueurActuel().getCoord());
        int tour = m.getTour();
        c1.monteEaux();
        c1.monteEaux();
        Assertions.assertFalse(m.tourPeutFinir());
        m.tourSuivant();
        Assertions.assertEquals(tour, m.getTour());
        m.getJoueurActuel().deplace(Direction.DROITE);
        Assertions.assertTrue(m.tourPeutFinir());
        m.tourSuivant();
        Assertions.assertEquals(tour + 1, m.getTour());
    }
}
