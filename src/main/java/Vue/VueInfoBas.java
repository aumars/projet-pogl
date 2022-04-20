package Vue;

import Modele.*;
import javax.swing.*;
import java.awt.*;

public class VueInfoBas extends JPanel {
    private final Modele modele;

    private JPanel container = new JPanel();
    public VueCommande vue_commande;
    public VueFinJeu vue_fin_jeu;
    public VueAide vue_aide;
    public VueLog vue_log;

    private boolean affiche_aide = false;
    private boolean est_fin_jeu = false;

    /**
     * Affiche le menu du bas où sont afficher les commandes, le menu de fin et le
     * menu d'aide.
     * 
     * @param m Le modele.
     */
    public VueInfoBas(Modele m) {
        this.modele = m;
        this.vue_commande = new VueCommande(this.modele);
        this.vue_aide = new VueAide();
        this.vue_log = new VueLog();

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.container.setLayout(new GridBagLayout());
        this.container.add(this.vue_commande, Utils.positionneGrille(0, 0));
        this.container.add(this.vue_log, Utils.positionneGrille(0, 1));
        this.add(this.container);
    }

    /**
     * Renvoie si le jeu est terminée.
     * 
     * @return True si le jeu est terminée. False sinon.
     */
    public boolean verifieFinJeu() {
        boolean jeuGagne = this.modele.verifieGagnants();

        if (this.modele.getFinJeu()) {
            this.container.setVisible(false);
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
            this.container.setVisible(false);
            // this.vue_commande.setVisible(false);
            this.add(this.vue_aide, BorderLayout.LINE_START);
        }

        else {
            this.container.setVisible(!this.est_fin_jeu);
            // this.vue_commande.setVisible(!this.est_fin_jeu);
            this.remove(this.vue_aide);
        }

        this.affiche_aide = !this.affiche_aide;
    }
}
