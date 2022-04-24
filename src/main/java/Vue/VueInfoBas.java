package Vue;

import Modele.*;
import javax.swing.*;
import java.awt.*;

/**
 * L'interface graphique en bas de la fenêtre
 */
public class VueInfoBas extends JPanel {
    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * JPanel de l'interface graphique
     */
    private final JPanel CONTAINER = new JPanel();

    /**
     * L'interface graphique de la commande
     */
    public VueCommande vue_commande;

    /**
     * L'interface graphique de la fin du jeu
     */
    public VueFinJeu vue_fin_jeu;

    /**
     * L'interface graphique de l'aide
     */
    public VueAide vue_aide;

    /**
     * L'interface graphique du log
     */
    public VueLog vue_log;

    /**
     * Drapeau pour afficher l'aide
     */
    private boolean affiche_aide = false;

    /**
     * Drapeau pour marqueur la fin du jeu
     */
    private boolean est_fin_jeu = false;

    /**
     * Affiche le menu du bas où sont afficher les commandes, le menu de fin et le
     * menu d'aide.
     * 
     * @param m Le modele.
     */
    public VueInfoBas(Modele m) {
        this.MODELE = m;
        this.vue_commande = new VueCommande(this.MODELE);
        this.vue_aide = new VueAide();
        this.vue_log = new VueLog();

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.CONTAINER.setLayout(new GridBagLayout());
        this.CONTAINER.add(this.vue_commande, Utils.positionneGrille(0, 0));
        this.CONTAINER.add(this.vue_log, Utils.positionneGrille(0, 1));
        this.add(this.CONTAINER);
    }

    /**
     * Renvoie si le jeu est terminée.
     * 
     * @return True si le jeu est terminée. False sinon.
     */
    public boolean verifieFinJeu() {
        boolean jeuGagne = this.MODELE.verifieGagnants();

        if (jeuGagne || this.MODELE.getFinJeu()) {
            this.CONTAINER.setVisible(false);
            this.vue_fin_jeu = new VueFinJeu(!jeuGagne);
            this.est_fin_jeu = true;
            this.add(this.vue_fin_jeu);
            return true;
        }

        return false;
    }

    /**
     * Affiche le panneau d'aide.
     */
    public void affichePanneauAide() {
        if (!affiche_aide) {
            this.CONTAINER.setVisible(false);
            this.add(this.vue_aide, BorderLayout.LINE_START);
        }

        else {
            this.CONTAINER.setVisible(!this.est_fin_jeu);
            this.remove(this.vue_aide);
        }

        this.affiche_aide = !this.affiche_aide;
    }
}
