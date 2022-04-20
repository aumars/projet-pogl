import Modele.Exception.InvalidGameException;
import Modele.Modele;
import Vue.Vue;

import Controleur.Controleur;

import java.awt.EventQueue;

public class IleInterdite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Modele modele;
            try {
                modele = new Modele("map1.txt", "game2.xml");
                Vue vue = new Vue(modele);
                Controleur controleur = new Controleur(modele, vue);
            } catch (InvalidGameException e) {
                e.printStackTrace();
            }
        });
    }
}
