package Modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrilleTest {
    Grille g0;

    @BeforeEach
    void grilleInit() {
        this.g0 = new Grille(new Carte("map1.txt"));
    }

    @Test
    void grilleAddObjet() {
        Artefact a = new Artefact(Element.AIR);
        this.g0.addObjet(a, new Coord(1, 1));
        this.g0.addObjet(a, new Coord(2, 1));
        Assertions.assertThrows(RuntimeException.class, () -> this.g0.addObjet(a, new Coord(0, 0)));
        Assertions.assertThrows(RuntimeException.class, () -> this.g0.addObjet(a, new Coord(1, 1)));
    }
}
