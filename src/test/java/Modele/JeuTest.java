package Modele;

import Modele.Exception.InvalidGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class JeuTest {
    Jeu jeu1;
    @BeforeEach
    void jeuInit() throws ParserConfigurationException, IOException, SAXException, InvalidGameException {
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
    void jeuUneCaseDeuxObjets() throws ParserConfigurationException, IOException, SAXException {
        Assertions.assertThrows(InvalidGameException.class, () -> new Jeu("gameBad1.xml"));
    }
}
