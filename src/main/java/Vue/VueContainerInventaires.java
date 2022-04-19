package Vue;

import Modele.Modele;

import javax.swing.*;

public class VueContainerInventaires extends JPanel {
    private Modele modele;
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
        int nb_joueurs = this.modele.getNbJoueurs();
        this.inventaires = new VueInventaire[nb_joueurs];

        for (int i = 0; i < nb_joueurs; i++) {
            this.inventaires[i] = new VueInventaire(this.modele, i);
            this.add(this.inventaires[i]);
        }
    }
}
