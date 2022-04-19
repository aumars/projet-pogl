package Vue;

import Modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueAide extends JPanel {
    /**
     * Affichage du menu d'aide.
     * 
     * @param m Le modele du jeu.
     */
    public VueAide(Modele m) {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setLayout(new GridLayout(1, 3));

        JPanel deplacement = new JPanel();
        deplacement.setLayout(new BoxLayout(deplacement, BoxLayout.Y_AXIS));
        deplacement.add(getTitre("Déplacements :"));
        deplacement.add(getLabel("Monter: [Z] ou [Flèche Haut]"));
        deplacement.add(getLabel("Descendre: [Q] ou [Flèche Gauche]"));
        deplacement.add(getLabel("Droite: [S] ou [Flèche Bas]"));
        deplacement.add(getLabel("Gauche: [D] ou [Flèche Droite]"));
        this.add(deplacement);

        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
        actions.add(getTitre("Actions :"));
        actions.add(getLabel("Chercher une clef: [A]"));
        actions.add(getLabel("Prendre un artefact: [F]"));
        actions.add(getLabel("Secher une case: [Cliquer sur la case]"));
        actions.add(getLabel("Se teleporter: [Cliquer sur la case]"));
        actions.add(getLabel("Ensabler une case: [Cliquer sur la case]"));
        this.add(actions);

        JPanel general = new JPanel();
        general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
        general.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
        general.add(getTitre("Générals :"));
        general.add(getLabel("Afficher l'aide: [H]"));
        general.add(getLabel("Fermer le jeu: [ECHAPPE]"));
        general.add(getLabel("Finir le tour: [ENTER]"));
        this.add(general);
    }

    /**
     * Creer un composent JLabel pour afficher les commandes.
     * 
     * @param text le texte du label.
     * @return le composent JLabel au format commande.
     */
    private JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));
        return label;
    }

    /**
     * Creer un composent JLabel pour afficher les titres.
     * 
     * @param text le texte du label.
     * @return le composent JLabel au format titre.
     */
    private JLabel getTitre(String text) {
        JLabel label = new JLabel(Utils.souligneLabel(text));
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 13));
        return label;
    }
}
