package Modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class CaseTest {
    Grille g;
    Case c0, c1, c2;
    Case g0, g1, g2;

    @BeforeEach
    void caseInit() {
        this.g = new Grille(new Carte("map1.txt"));
        this.c0 = new Case(new Coord(0, 0), Terrain.TERRE, null);
        this.c1 = new Case(new Coord(1, 0), Terrain.MER, null);
        this.c2 = new Case(new Coord(0, 1), Terrain.HELIPAD, null);
        this.g0 = this.g.getCase(0, 0);
        this.g1 = this.g.getCase(1, 0);
        this.g2 = this.g.getCase(0, 1);
    }

    @Test
    void caseEstTraversable() {
        Assertions.assertTrue(this.c0.estTraversable());
        Assertions.assertFalse(this.c1.estTraversable());
        Assertions.assertTrue(this.c2.estTraversable());
    }

    @Test
    void caseInondeeEstTraversable() {
        this.c0.monteEaux();
        Assertions.assertTrue(this.c0.estTraversable());
        this.c0.monteEaux();
        Assertions.assertFalse(this.c0.estTraversable());
    }

    @Test
    void caseEstHelipad() {
        Assertions.assertFalse(this.c0.estHelipad());
        Assertions.assertFalse(this.c1.estHelipad());
        Assertions.assertTrue(this.c2.estHelipad());
    }

    @Test
    void caseAjoutObjet() {
        Artefact a = new Artefact(Element.AIR);
        this.c0.ajoutObjet(a);
        Assertions.assertThrows(RuntimeException.class, () -> this.c0.ajoutObjet(a));
        Assertions.assertThrows(RuntimeException.class, () -> this.c1.ajoutObjet(a));
        Assertions.assertThrows(RuntimeException.class, () -> this.c2.ajoutObjet(a));
    }

    @Test
    void caseAObjet() {
        Artefact a = new Artefact(Element.AIR);
        Assertions.assertFalse(this.c0.aObjet());
        Assertions.assertFalse(this.c0.aObjet(Artefact.class));
        this.c0.ajoutObjet(a);
        Assertions.assertTrue(this.c0.aObjet(Artefact.class));
    }

    @Test
    void caseGetObjet() {
        Assertions.assertNull(this.c0.getObjet());
        Artefact a = new Artefact(Element.AIR);
        this.c0.ajoutObjet(a);
        Assertions.assertSame(a, this.c0.getObjet());
    }

    @Test
    void caseGetObjetVisibiliteArtefact() {
        Artefact a = new Artefact(Element.AIR);
        this.c0.ajoutObjet(a);
        Assertions.assertTrue(this.c0.getObjetVisibilite());
    }

    @Test
    void caseGetObjetVisibiliteClef() {
        Clef c = new Clef(Element.AIR);
        this.c0.ajoutObjet(c);
        Assertions.assertFalse(this.c0.getObjetVisibilite());
        this.c0.detruitObjet();
        Assertions.assertTrue(this.c0.getObjetVisibilite());
    }

    @Test
    void caseAdjacentNoGrille() {
        Assertions.assertThrows(RuntimeException.class, () -> this.c0.adjacent(Direction.HAUT));
        Assertions.assertThrows(RuntimeException.class, () -> this.c0.adjacent(Direction.BAS));
        Assertions.assertThrows(RuntimeException.class, () -> this.c0.adjacent(Direction.DROITE));
        Assertions.assertSame(this.c0, this.c0.adjacent(Direction.NEUTRE));
    }

    @Test
    void caseAdjacent() {
        Assertions.assertSame(this.g1, this.g0.adjacent(Direction.DROITE));
        Assertions.assertSame(this.g2, this.g0.adjacent(Direction.BAS));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.g0.adjacent(Direction.HAUT));
    }
}
