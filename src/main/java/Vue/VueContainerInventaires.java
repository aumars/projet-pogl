package Vue;

import Modele.*;

import javax.swing.*;
import java.util.List;

/**
 * L'interface graphique de l'ensemble des inventaires
 */
public class VueContainerInventaires extends JPanel {
    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * Une liste des interfaces graphiques des inventaires de tous les joueurs.
     */
    public VueInventaire[] inventaires;

    /**
     * Affiche un container d'inventaire.
     *
     * @param m Le modele.
     */
    public VueContainerInventaires(Modele m) {
        this.MODELE = m;
        this.afficheInventaires();
    }

    /**
     * Affiche un inventaire pour chaque joueur.
     */
    private void afficheInventaires() {
        List<Joueur> joueurList = this.MODELE.getJoueurs();
        int nb_joueurs = this.MODELE.getNbJoueurs();
        this.inventaires = new VueInventaire[nb_joueurs];
        
        for (int i = 0; i < nb_joueurs; i++) {
            this.inventaires[i] = new VueInventaire(this.MODELE, joueurList.get(i));
            this.add(this.inventaires[i]);
        }
    }
}
