package Modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordTest {
    Coord c0, c1;

    @BeforeEach
    void coordInit() {
        this.c0 = new Coord(0, 0);
        this.c1 = new Coord(1, 2);
    }

    @Test
    void coordInitInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coord(-1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coord(0, -1));
    }

    @Test
    void coordToString() {
        Assertions.assertEquals("(0, 0)", this.c0.toString());
        Assertions.assertEquals("(1, 2)", this.c1.toString());
    }

    @Test
    void coordEquals() {
        Assertions.assertEquals(this.c0, this.c0);
        Assertions.assertEquals(this.c0, new Coord(0, 0));
        Assertions.assertNotEquals(this.c0, new Coord(1, 0));
    }

    @Test
    void coordHashCode() {
        Coord c = new Coord(0, 0);
        Assertions.assertEquals(this.c0.hashCode(), this.c0.hashCode());
        Assertions.assertEquals(this.c0.hashCode(), c.hashCode());
        Assertions.assertNotEquals(this.c0.hashCode(), this.c1.hashCode());
    }

    @Test
    void coordAdjacent() {
        Assertions.assertEquals(new Coord(0, 0), this.c0.adjacent(Direction.NEUTRE));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.c0.adjacent(Direction.HAUT));
        Assertions.assertEquals(new Coord(1, 0), this.c0.adjacent(Direction.DROITE));
        Assertions.assertEquals(new Coord(0, 1), this.c0.adjacent(Direction.BAS));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.c0.adjacent(Direction.GAUCHE));
    }
}
