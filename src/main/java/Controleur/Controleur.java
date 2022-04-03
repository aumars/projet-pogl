package Controleur;

import Modele.Modele;
import Vue.Vue;

import java.util.Observable;

public class Controleur extends Observable {
    private Modele modele;
    private Vue vue;
    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;
    }
}
