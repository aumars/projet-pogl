import Modele.Modele;
import Vue.Vue;

import java.awt.EventQueue;

public class IleInterdite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Modele modele = new Modele();
            Vue vue = new Vue(modele);
        });
    }
}
