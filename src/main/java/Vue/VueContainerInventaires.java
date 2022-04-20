package Vue;

import Modele.*;

import javax.swing.*;
import java.util.List;

public class VueContainerInventaires extends JPanel {
    private final Modele modele;
    public VueInventaire[] inventaires;

    /**
     * Affiche un container d'inventaire.
     *
     * @param m Le modele.
     */
    public VueContainerInventaires(Modele m) {
        this.modele = m;
        this.afficheInventaires();
    }

    /**
     * Affiche un inventaire pour chaque joueur.
     */
    private void afficheInventaires() {
        List<Joueur> joueurList = this.modele.getJoueurs();
        int nb_joueurs = this.modele.getNbJoueurs();
        this.inventaires = new VueInventaire[nb_joueurs];
        
        for (int i = 0; i < nb_joueurs; i++) {
            this.inventaires[i] = new VueInventaire(this.modele, joueurList.get(i));
            this.add(this.inventaires[i]);
        }
    }
}
