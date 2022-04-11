import Modele.Modele;
import Vue.Vue;
import org.xml.sax.SAXException;

import Controleur.Controleur;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.EventQueue;
import java.io.IOException;

public class IleInterdite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Modele modele;
            try {
                modele = new Modele("map1.txt", "game1.xml");
            } catch (ParserConfigurationException | IOException | SAXException e) {
                throw new RuntimeException();
            }

            Vue vue = new Vue(modele);
            Controleur controleur = new Controleur(modele, vue);
        });
    }
}
