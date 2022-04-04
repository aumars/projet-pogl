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
    private final Grille grille;
    private final List<Joueur> ensemble;
    private Iterator<Joueur> iter;
    private int tour;
    private Joueur joueurActuel;

    public Modele (String map_path, String game_path) throws ParserConfigurationException, IOException, SAXException {
        this(parseMap(map_path), parseGame(game_path));
    }

    public Modele(char[][] map, Document game) {
        this.hauteur = map.length;
        this.largeur = map[0].length;
        this.grille = new Grille(this.hauteur, this.largeur, map, this.parseGameObjet(game));
        this.ensemble = this.parseGameJoueur(game);
        this.tour = 1;
        this.joueurActuel = this.ensemble.get(0);
        this.iter = this.ensemble.iterator();
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

    private List<Joueur> parseGameJoueur(Document game) {
        List<Joueur> s = new ArrayList<>();
        NodeList list = game.getElementsByTagName("personnage");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                String id = element.getAttribute("id");
                Coord pos = this.parsePosition(element.getElementsByTagName("position"));
                Joueur p = Joueur.joueurById(id, this.grille.getCase(pos));
                s.add(p);
            }
        }
        return s;
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

    public Joueur getJoueurActuel() { return this.joueurActuel; }

    public boolean tourSuivant() {
        this.tour++;
        this.grille.inonde();
        for (Joueur joueur: this.ensemble) { joueur.noie(); }
        if (this.ensemble.stream().noneMatch(joueur -> joueur.estVivant())) {
            return false;
        }
        this.joueurActuel = this.prochainJoueurVivant();
        this.joueurActuel.newTurn();
        return true;
    }

    public int getTour() { return this.tour; }

    public boolean verifieGagnants() {
        return this.ensemble.stream().anyMatch(j -> j.verifieGagnant());
    }

    private Joueur prochainJoueurVivant() {
        if (!this.iter.hasNext()) {
            this.iter = this.ensemble.iterator();
        }
        Joueur joueur = this.iter.next();
        while (!joueur.estVivant()) {
            joueur = this.iter.next();
            if (!this.iter.hasNext()) {
                this.iter = this.ensemble.iterator();
            }
        }
        return joueur;
    }
}