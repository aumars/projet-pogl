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
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Une classe qui modélise les coordonnées de tous les {@link Objet}s et
 * {@link Joueur}s dans un jeu.
 */
public final class Jeu {
    /**
     * Une liste qui associe des {@link Objet}s à son {@link Coord}.
     */
    public final List<SimpleImmutableEntry<Objet, Coord>> objets;

    /**
     * Une liste qui associe des {@link Joueur}s à son {@link Coord}.
     */
    public final List<SimpleImmutableEntry<Joueur, Coord>> ensemble;

    /**
     * Modélise un jeu (avec {@link Joueur}s) à partir d'un fichier XML.
     * 
     * @param game_path Chemin du fichier XML.
     * @throws InvalidGameException S'il existe une erreur avec le fichier XML.
     */
    public Jeu(String game_path) throws InvalidGameException {
        this(game_path, false);
    }

    /**
     * Modélise un jeu (avec {@link Joueur}s) à partir d'un fichier XML.
     * 
     * @param game_path Chemin du fichier XML.
     * @param vide      S'il y a des {@link Joueur}s dans le jeu.
     * @throws InvalidGameException S'il existe une erreur avec le fichier XML.
     */
    public Jeu(String game_path, boolean vide) throws InvalidGameException {
        Document game;
        try {
            game = parseGame(game_path);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new InvalidGameException(e.getMessage(), e.getCause());
        }
        this.objets = parseGameObjet(game);
        if (vide) {
            this.ensemble = new ArrayList<>();
        } else {
            this.ensemble = parseGameJoueur(game);
        }
    }

    /**
     * Lit un fichier XML.
     * 
     * @param game_path Chemin du fichier XML.
     * @return Le document XML.
     */
    private static Document parseGame(String game_path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(Modele.class.getClassLoader().getResourceAsStream(game_path));
    }

    /**
     * Lit le document XML pour les {@link Objet}s et leur position sur un plan.
     * 
     * @param game Document XML.
     * @return Liste de pairs d'{@link Objet} et d'{@link Coord} associée.
     * @throws InvalidGameException Deux {@link Objet}s sont sur le même
     *                              {@link Coord}.
     */
    private static List<SimpleImmutableEntry<Objet, Coord>> parseGameObjet(Document game)
            throws InvalidGameException {
        List<SimpleImmutableEntry<Objet, Coord>> a = new ArrayList<>();
        NodeList list = game.getElementsByTagName("objet");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                String objet_element = element.getElementsByTagName("element").item(0).getTextContent();
                Coord pos = parsePosition(element.getElementsByTagName("position"));
                for (SimpleImmutableEntry<Objet, Coord> ex : a) {
                    if (ex.getValue().equals(pos)) {
                        throw new InvalidGameException(String.format("Deux objets sont sur le même point %s", pos));
                    }
                }
                Element e = elementById(objet_element);
                Objet o = objetsByID(id, e);
                a.add(new SimpleImmutableEntry<>(o, pos));
            }
        }
        return a;
    }

    /**
     * Lit le document XML pour les {@link Joueur}s et leur position sur un plan.
     * 
     * @param game Document XML.
     * @return Liste de pairs d'{@link Joueur} et d'{@link Coord} associée.
     */
    private static List<SimpleImmutableEntry<Joueur, Coord>> parseGameJoueur(Document game) {
        List<SimpleImmutableEntry<Joueur, Coord>> s = new ArrayList<>();
        NodeList list = game.getElementsByTagName("personnage");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                Coord pos = parsePosition(element.getElementsByTagName("position"));
                Joueur j = joueurById(id);
                s.add(new SimpleImmutableEntry<>(j, pos));
            }
        }
        return s;
    }

    /**
     * Construit un {@link Coord}.
     * 
     * @param pos Position du noeud XML "position".
     * @return Un {@link Coord}
     */
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

    /**
     * Associe l'élément à une chaine de caractères donnée.
     * 
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
     * 
     * @param id Une chaine de caractères
     * @param e  Un {@link Element}
     * @return Un {@link Objet}
     * @exception IllegalArgumentException Si {@code s} n'est pas reconnu.
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

    /**
     * Construit un {@link Joueur} selon son id.
     * 
     * @param name Une chaine de caractères
     * @return Un {@link Joueur}
     */
    private static Joueur joueurById(String name) {
        if (name.equals("joueur")) {
            return new Joueur();
        } else {
            throw new IllegalArgumentException(String.format("%s n'est pas un joueur reconnu", name));
        }
    }
}
