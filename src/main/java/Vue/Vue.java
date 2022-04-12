package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private Modele modele;

    public VueState state;
    public VueInventory inventory;
    private VueGrille grille;
    public VueCommande commande;

    public Vue(Modele m) {
        this.modele = m;

        this.frame = new JFrame();
        this.frame.setTitle("L'Ile Interdite");
        this.frame.setLayout(new GridBagLayout());
        this.frame.setResizable(false);

        this.state = new VueState(this.modele);
        this.inventory = new VueInventory(this.modele, "Player 1");
        this.grille = new VueGrille(this.modele);
        this.commande = new VueCommande(this.modele);

        this.frame.add(this.state, Utils.positionGrid(0, 0, 2, 1));
        this.frame.add(this.inventory, Utils.positionGrid(0, 1, 1, 1));
        this.frame.add(this.grille, Utils.positionGrid(1, 1, 1, 1));
        this.frame.add(this.commande, Utils.positionGrid(0, 2, 2, 1));

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}