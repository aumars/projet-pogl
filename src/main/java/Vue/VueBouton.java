package Vue;

import java.awt.*;
import javax.swing.*;
import java.lang.String;

public class VueBouton extends JButton {
    /**
     * Affichage d'un bouton.
     * 
     * @param tooltip Le texte à afficher lorsque la souris survole le bouton.
     */
    public VueBouton(String tooltip) {
        this.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 18));
        this.setBackground(ConstsValue.COLOR_DEFAULT);
        this.setFocusable(false);
        this.setToolTipText(tooltip);
    }

    /**
     * Affichage d'un bouton.
     * 
     * @param tooltip Le texte à afficher lorsque la souris survole le bouton.
     * @param text    Le texte du bouton.
     */
    public VueBouton(String tooltip, String text) {
        this(tooltip);
        this.setText(text);
    }

    /**
     * Affichage d'un bouton.
     * 
     * @param tooltip Le texte à afficher lorsque la souris survole le bouton.
     * @param icon    L'icone du bouton.
     */
    public VueBouton(String tooltip, Icon icon) {
        this(tooltip);
        this.setIcon(icon);
    }

    /**
     * Affichage d'un bouton.
     * 
     * @param tooltip Le texte à afficher lorsque la souris survole le bouton.
     * @param text    Le texte du bouton.
     * @param icon    L'icone du bouton.
     */
    public VueBouton(String tooltip, String text, Icon icon) {
        this(tooltip);
        this.setIcon(icon);
        this.setText(text);
    }
}
