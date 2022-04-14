package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private Modele modele;

    public VueState state;
    public VueInventory inventory;
    public VueGrille grille;
    public VueCommande commande;
    public VueBottom bottom;
    public VueEndGame end_game;

    public Vue(Modele m) {
        this.modele = m;

        this.frame = new JFrame();
        this.frame.setTitle("L'Ile Interdite");
        this.frame.setLayout(new GridBagLayout());
        this.frame.setResizable(false);

        this.state = new VueState(this.modele);
        this.inventory = new VueInventory(this.modele, "<HTML><U>"+this.modele.getJoueurActuel().nom+"</U></HTML>");
        this.grille = new VueGrille(this.modele);
        this.bottom = new VueBottom(this.modele);
        
        this.frame.add(this.state, Utils.positionGrid(0, 0, 2, 1, 1));
        this.frame.add(this.inventory, Utils.positionGrid(0, 1, 1, 3, 0));
        this.frame.add(this.grille, Utils.positionGrid(1, 1));
        this.frame.add(this.bottom, Utils.positionGrid(1, 2));
        
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
    
    public JFrame getFrame(){
        return this.frame;
    }
}