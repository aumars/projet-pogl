package Modele;

import Modele.Exception.InvalidGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JeuTest {
    Jeu jeu1;
    @BeforeEach
    void jeuInit() throws InvalidGameException {
        this.jeu1 = new Jeu("game1.xml");
    }

    @Test
    void jeuUneCaseUnObjet() {
        List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> objets = this.jeu1.objets;
        Set<Coord> coords = new HashSet<>();
        for (AbstractMap.SimpleImmutableEntry<Objet, Coord> pair: objets) {
            coords.add(pair.getValue());
        }
        Assertions.assertEquals(objets.size(), coords.size());
    }

    @Test
    void jeuUneCaseDeuxObjets() {
        Assertions.assertThrows(InvalidGameException.class, () -> new Jeu("gameBad1.xml"));
    }
}
