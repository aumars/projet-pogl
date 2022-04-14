package Modele;

import Modele.Exception.InvalidGameException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Jeu {
    public final List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> objets;
    public final List<AbstractMap.SimpleImmutableEntry<Joueur, Coord>> ensemble;

    public Jeu(String game_path) throws ParserConfigurationException, IOException, SAXException, InvalidGameException {
        this(game_path, false);
    }

    public Jeu(String game_path, boolean vide) throws ParserConfigurationException, IOException, SAXException, InvalidGameException {
        Document game = parseGame(game_path);
        this.objets = parseGameObjet(game);
        if (vide) {
            this.ensemble = new ArrayList<>();
        }
        else {
            this.ensemble = parseGameJoueur(game);
        }
    }

    private static Document parseGame(String game_path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(game_path)));
    }

    private static List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> parseGameObjet(Document game) throws InvalidGameException {
        List<AbstractMap.SimpleImmutableEntry<Objet, Coord>> a = new ArrayList<>();
        NodeList list = game.getElementsByTagName("objet");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                String objet_element = element.getElementsByTagName("element").item(0).getTextContent();
                Coord pos = parsePosition(element.getElementsByTagName("position"));
                for (AbstractMap.SimpleImmutableEntry<Objet, Coord> ex: a) {
                    if (ex.getValue().equals(pos)) {
                        throw new InvalidGameException();
                    }
                }
                Element e = elementById(objet_element);
                Objet o = objetsByID(id, e);
                a.add(new AbstractMap.SimpleImmutableEntry<>(o, pos));
            }
        }
        return a;
    }

    /**
     * Associe l'élément à une chaine de caractères donnée.
     * @param s Chaine de caractères associée à un élément.
     * @return L'élément associé.
     */
    private static Element elementById(String s) {
        switch (s) {
            case "AIR":
                return Element.AIR;
            case "EAU":
                return Element.EAU;
            case "TERRE":
                return Element.TERRE;
            case "FEU":
                return Element.FEU;
            default:
                throw new IllegalArgumentException(String.format("s = %s n'est pas reconnu comme un élément", s));
        }
    }

    /**
     * Construit un objet associé à un élément selon son id.
     * @param id
     * @param e
     * @return
     * @exception IllegalArgumentException If `s` is not recognised.
     */
    private static Objet objetsByID(String id, Element e) {
        switch (id) {
            case "artefact":
                return new Artefact(e);
            case "clef":
                return new Clef(e);
            default:
                throw new IllegalArgumentException(String.format("%s n'est pas un objet reconnu.", id));
        }
    }

    private static List<AbstractMap.SimpleImmutableEntry<Joueur, Coord>> parseGameJoueur(Document game) {
        List<AbstractMap.SimpleImmutableEntry<Joueur, Coord>> s = new ArrayList<>();
        NodeList list = game.getElementsByTagName("personnage");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                Coord pos = parsePosition(element.getElementsByTagName("position"));
                Joueur j = joueurById(id);
                s.add(new AbstractMap.SimpleImmutableEntry<>(j, pos));
            }
        }
        return s;
    }

    private static Joueur joueurById(String name) {
        if (name.equals("joueur")) {
            return new Joueur();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static Coord parsePosition(NodeList pos) {
        for (int i = 0; i < pos.getLength(); i++) {
            Node node = pos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                int x = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
                int y = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());
                return new Coord(x, y);
            }
        }
        throw new RuntimeException();
    }
}
