package Controleur;

import Modele.*;
import Vue.*;
import java.awt.event.*;

public class Controleur implements ActionListener{
    private Modele modele;
    private Vue vue;
    
    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;

        this.vue.commande.btn_move_top.addActionListener(this);
        this.vue.commande.btn_move_bottom.addActionListener(this);
        this.vue.commande.btn_move_left.addActionListener(this);
        this.vue.commande.btn_move_right.addActionListener(this);

        this.vue.commande.btn_assecher_top.addActionListener(this);
        this.vue.commande.btn_assecher_bottom.addActionListener(this);
        this.vue.commande.btn_assecher_left.addActionListener(this);
        this.vue.commande.btn_assecher_right.addActionListener(this);
        this.vue.commande.btn_assecher_center.addActionListener(this);

        this.vue.commande.btn_next.addActionListener(this);
        this.vue.commande.btn_clef.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        // Gere le deplacement du joueurs.
        if(this.vue.commande.btn_move_top.getModel().isArmed()){
            this.modele.getJoueurActuel().deplace(Direction.HAUT);
        }
        
        if(this.vue.commande.btn_move_bottom.getModel().isArmed()){
            this.modele.getJoueurActuel().deplace(Direction.BAS);
        }

        if(this.vue.commande.btn_move_left.getModel().isArmed()){
            this.modele.getJoueurActuel().deplace(Direction.GAUCHE);
        }

        if(this.vue.commande.btn_move_right.getModel().isArmed()){
            this.modele.getJoueurActuel().deplace(Direction.DROITE);
        }
        
        // Gere l'assechement du sol.
        if(this.vue.commande.btn_assecher_top.getModel().isArmed()){
            this.modele.getJoueurActuel().asseche(Direction.HAUT);
        }

        if(this.vue.commande.btn_assecher_bottom.getModel().isArmed()){
            this.modele.getJoueurActuel().asseche(Direction.BAS);
        }

        if(this.vue.commande.btn_assecher_left.getModel().isArmed()){
            this.modele.getJoueurActuel().asseche(Direction.GAUCHE);
        }

        if(this.vue.commande.btn_assecher_right.getModel().isArmed()){
            this.modele.getJoueurActuel().asseche(Direction.DROITE);
        }

        // Gere les actions de la partie.
        if(this.vue.commande.btn_next.getModel().isArmed()){
            this.modele.tourSuivant();
            this.vue.state.update();
        }

        if(this.vue.commande.btn_clef.getModel().isArmed()){
            this.modele.getJoueurActuel().chercheCle();
            // this.vue.state.update();
        }

    }
}
