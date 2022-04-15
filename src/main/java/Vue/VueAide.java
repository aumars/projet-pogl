package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

public class VueAide extends JPanel{
    public VueAide(Modele m){
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setLayout(new GridLayout(1, 3));

        JPanel deplacement = new JPanel();
        deplacement.setLayout(new BoxLayout(deplacement, BoxLayout.Y_AXIS));
        deplacement.add(titleLabel("Déplacements :"));
        deplacement.add(customLabel("Monter: [Z] ou [Flèche Haut]"));
        deplacement.add(customLabel("Descendre: [Q] ou [Flèche Gauche]"));
        deplacement.add(customLabel("Droite: [S] ou [Flèche Bas]"));
        deplacement.add(customLabel("Gauche: [D] ou [Flèche Droite]"));
        this.add(deplacement);
        
        JPanel secher = new JPanel();
        secher.setLayout(new BoxLayout(secher, BoxLayout.Y_AXIS));
        secher.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
        secher.add(titleLabel("Sécher une case :"));
        secher.add(customLabel("Haut: [O]"));
        secher.add(customLabel("Bas: [K]"));
        secher.add(customLabel("Centre: [SPACE]"));
        secher.add(customLabel("Droite: [L]"));
        secher.add(customLabel("Gauche: [M]"));
        this.add(secher);
        
        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
        actions.add(titleLabel("Actions :"));
        actions.add(customLabel("Chercher une clef: [A]"));
        actions.add(customLabel("Prendre un artefact: [F]"));
        actions.add(customLabel("Fin du tour: [ENTER]"));
        actions.add(customLabel("Afficher l'aide: [H]"));
        this.add(actions);
    }
    
    private JLabel customLabel(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("", Font.PLAIN, 13));
        return label;
    }

    private JLabel titleLabel(String text){
        JLabel label = new JLabel("<html><u>"+text+"</u></html>");
        label.setFont(new Font("", Font.BOLD, 13));
        return label;   
    }
}
