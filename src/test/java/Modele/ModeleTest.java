package Modele;

import Modele.Exception.InvalidGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModeleTest {
    Modele modeleM1G1;
    @BeforeEach
    void modeleInit() throws InvalidGameException {
        this.modeleM1G1 = new Modele("map1.txt", "game1.xml");
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
    void modeleTestGame1() {
        this.modeleM1G1.getJoueurActuel().deplace(Direction.DROITE);
        this.modeleM1G1.tourSuivant();
        this.modeleM1G1.getJoueurActuel().deplace(Direction.DROITE);
        this.modeleM1G1.tourSuivant();
        this.modeleM1G1.getJoueurActuel().deplace(Direction.DROITE);
        this.modeleM1G1.tourSuivant();
        this.modeleM1G1.getJoueurActuel().deplace(Direction.BAS);
        Assertions.assertNotNull(this.modeleM1G1.getJoueurActuel().chercheCle());
    }
}
