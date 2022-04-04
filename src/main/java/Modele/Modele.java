package Modele;

import javafx.util.Pair;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Modele extends Observable {
    private final int largeur, hauteur;
    private Grille grille;
    private Set<Personnage> ensemble;
    private Joueur joueur;

    public Modele (String map_path, String game_path) throws ParserConfigurationException, IOException, SAXException {
        this(parseMap(map_path), parseGame(game_path));
    }

    public Modele(char[][] map, Document game) {
        this.hauteur = map.length;
        this.largeur = map[0].length;
        this.grille = new Grille(this.hauteur, this.largeur, map, this.parseGameObjet(game));
        Pair<Joueur, Set<Personnage>> p = this.parseGamePersonnage(game);
        this.ensemble = p.getValue();
        this.joueur = p.getKey();
    }

    private static char[][] parseMap(String map_path) {
        Scanner map_reader = new Scanner(Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(map_path)));
        int hauteur = map_reader.nextInt();
        int largeur = map_reader.nextInt();
        map_reader.skip("\n");
        char[][] map = new char[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            String line = map_reader.nextLine();
            for (int j = 0; j < largeur; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        return map;
    }

    private static Document parseGame(String game_path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document game = db.parse(Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(game_path)));
        return game;
    }

    private List<Pair<Objet, Coord>> parseGameObjet(Document game) {
        List<Pair<Objet, Coord>> a = new ArrayList<>();
        NodeList list = game.getElementsByTagName("objet");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                String objet_element = element.getElementsByTagName("element").item(0).getTextContent();
                Coord pos = this.parsePosition(element.getElementsByTagName("position"));
                Element e = Element.StringToElement(objet_element);
                Objet o = Objet.objetByID(id, e);
                a.add(new Pair<>(o, pos));
            }
        }
        return a;
    }

    private Pair<Joueur, Set<Personnage>> parseGamePersonnage(Document game) {
        Pair<Joueur, Set<Personnage>> pair;
        Joueur j = null;
        Set<Personnage> s = new HashSet<>();
        NodeList list = game.getElementsByTagName("personnage");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                Coord pos = this.parsePosition(element.getElementsByTagName("position"));
                Personnage p = Personnage.personnageById(id, pos);
                s.add(p);
                if (p instanceof Joueur) {
                    j = (Joueur) p;
                }
            }
        }
        if (j == null) {
            throw new RuntimeException("No joueur !");
        }
        return new Pair<>(j, s);
    }

    private Coord parsePosition(NodeList pos) {
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

    public boolean deplaceJoueur(Direction dir) {
        int x = this.joueur.coord.x();
        int y = this.joueur.coord.y();
        switch (dir) {
            case HAUT: x--; break;
            case BAS: x++; break;
            case GAUCHE: y--; break;
            case DROITE: y++; break;
        }
        if (this.grille.estTraversable(x, y)) {
            this.joueur.coord.translate(dir);
            return true;
        }
        else {
            return false;
        }
    }

    public Coord getJoueurPosition() {
        return this.joueur.coord;
    }
}