package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

public class VueAide extends JPanel {
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

        JPanel secher = new JPanel();
        secher.setLayout(new BoxLayout(secher, BoxLayout.Y_AXIS));
        secher.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
        secher.add(getTitre("Sécher une case :"));
        secher.add(getLabel("Haut: [O]"));
        secher.add(getLabel("Bas: [K]"));
        secher.add(getLabel("Centre: [ESPACE]"));
        secher.add(getLabel("Droite: [L]"));
        secher.add(getLabel("Gauche: [M]"));
        this.add(secher);

        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
        actions.add(getTitre("Actions :"));
        actions.add(getLabel("Chercher une clef: [A]"));
        actions.add(getLabel("Prendre un artefact: [F]"));
        actions.add(getLabel("Fin du tour: [ENTER]"));
        actions.add(getLabel("Afficher l'aide: [H]"));
        actions.add(getLabel("Fermer le jeu: [ECHAPPE]"));
        this.add(actions);
    }

    private JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));
        return label;
    }

    private JLabel getTitre(String text) {
        JLabel label = new JLabel(Utils.souligneLabel(text));
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 13));
        return label;
    }
}
