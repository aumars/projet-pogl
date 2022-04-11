package Vue;
import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private Modele modele;
    
    private VueState state;
    private VueGrille grille;
    public VueCommande commande;

    public Vue(Modele m) {
        this.modele = m;

        this.frame = new JFrame();
        this.frame.setTitle("L'Ile Interdite");
        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);

        this.state = new VueState(this.modele);        
        this.grille = new VueGrille(this.modele);        
        this.commande = new VueCommande(this.modele);

        this.frame.add(this.state, BorderLayout.PAGE_START);
        this.frame.add(this.grille, BorderLayout.CENTER);
        this.frame.add(this.commande, BorderLayout.PAGE_END);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}