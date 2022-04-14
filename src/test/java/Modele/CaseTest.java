package Modele;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class CaseTest {
    @Test
    void caseInitOrigine() {
        new Case(new Coord(0, 0), Terrain.TERRE, null);
    }

    @Test
    void casePossedeClef() {
        Case c = new Case(new Coord(0, 0), Terrain.TERRE, null);
        Assertions.assertFalse(c.aObjet(Clef.class));
    }
}
