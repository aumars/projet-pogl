package Modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JoueurTest {
    Grille g;
    Joueur j0, j1;

    @BeforeEach
    void joueurInit() {
        this.g = new Grille(new Carte("map1.txt"));
        this.j0 = new Joueur();
        this.j1 = new Joueur(this.g.getCase(new Coord(1, 1)));
    }

    @Test
    void joueurSurCaseTraversable() {
        Assertions.assertThrows(RuntimeException.class, () -> this.j0.surCaseTraversable());
        Assertions.assertTrue(this.j1.surCaseTraversable());
    }

    @Test
    void joueurPossedeClef() {
        Assertions.assertFalse(this.j0.possedeClef(Element.AIR));
        Assertions.assertFalse(this.j1.possedeClef(Element.AIR));
    }
}
