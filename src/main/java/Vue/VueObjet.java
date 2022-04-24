package Vue;

import Modele.*;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;

/**
 * L'interface graphique d'un objet
 */
public class VueObjet extends JPanel {

    /**
     * Affiche un objet.
     * 
     * @param o                  L'objet. Si null affiche une case vide.
     * @param foreground         La couleur du texte.
     * @param afficheDescription Affiche un texte descriptifs de l'objet.
     */
    public VueObjet(Objet o, Color foreground, boolean afficheDescription) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel icon = new JLabel();
        JLabel description = new JLabel("", SwingConstants.CENTER);
        description.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 11));
        description.setForeground(foreground);

        int SIZE = 40;
        if (o == null) {
            icon.setIcon(Utils.tailleImg(ConstsIcon.PLH_VIDE, SIZE, SIZE));
            icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 13, 0));
        }

        else if (o.getClass().equals(Clef.class)) {
            icon.setIcon(Utils.tailleImg(ConstsIcon.CLEF, SIZE, SIZE));
            String pre_text = "Clef: ";

            if (afficheDescription)
                pre_text = "";

            description.setText(pre_text + o.ELEMENT.toString().toLowerCase());

            this.add(description);

        } else {
            switch (o.ELEMENT) {
                case EAU:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.EAU, SIZE, SIZE));
                    break;
                case AIR:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.AIR, SIZE, SIZE));
                    break;
                case FEU:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.FEU, SIZE, SIZE));
                    break;
                case TERRE:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.TERRE, SIZE, SIZE));
                    break;
                default:
                    break;
            }

            description.setText("Artefact: " + o.ELEMENT.toString().toLowerCase());
            if (afficheDescription)
                this.add(description);

        }

        this.add(icon);
    }

    /**
     * Affiche un objet.
     * 
     * @param o L'objet. Si null affiche une case vide.
     */
    public VueObjet(Objet o) {
        this(o, new Color(0, 0, 0), true);
    }

    /**
     * Affiche un objet.
     * 
     * @param o          L'objet. Si null affiche une case vide.
     * @param foreground Affiche un texte descriptifs de l'objet.
     */
    public VueObjet(Objet o, Color foreground) {
        this(o, foreground, true);
    }

    /**
     * Affiche un objet.
     * 
     * @param o                  L'objet. Si null affiche une case vide.
     * @param afficheDescription Affiche un texte descriptifs de l'objet.
     */
    public VueObjet(Objet o, boolean afficheDescription) {
        this(o, new Color(0, 0, 0), afficheDescription);
    }
}
