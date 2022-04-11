package Controleur;

import Modele.*;
import Vue.*;
public class Controleur {
    private Modele modele;
    private Vue vue;
    
    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;

        this.vue.commande.btn_move_top.addActionListener(e -> this.modele.getJoueurActuel().deplace(Direction.HAUT));
        this.vue.commande.btn_move_bottom.addActionListener(e -> this.modele.getJoueurActuel().deplace(Direction.BAS));
        this.vue.commande.btn_move_left.addActionListener(e -> this.modele.getJoueurActuel().deplace(Direction.GAUCHE));
        this.vue.commande.btn_move_right.addActionListener(e -> this.modele.getJoueurActuel().deplace(Direction.DROITE));

        this.vue.commande.btn_assecher_top.addActionListener(e -> this.modele.getJoueurActuel().asseche(Direction.HAUT));
        this.vue.commande.btn_assecher_bottom.addActionListener(e -> this.modele.getJoueurActuel().asseche(Direction.BAS));
        this.vue.commande.btn_assecher_left.addActionListener(e -> this.modele.getJoueurActuel().asseche(Direction.GAUCHE));
        this.vue.commande.btn_assecher_right.addActionListener(e -> this.modele.getJoueurActuel().asseche(Direction.DROITE));
        this.vue.commande.btn_assecher_center.addActionListener(e -> this.modele.getJoueurActuel().asseche(Direction.NEUTRE));
        
        this.vue.commande.btn_next.addActionListener(e -> this.modele.tourSuivant());
    }
}
