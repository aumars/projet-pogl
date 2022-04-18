package Vue;

import Modele.*;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;

public class VueObjet extends JPanel {
    private final int SIZE = 40;

    public VueObjet(Objet o, Color foreground, boolean afficheDescription) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel icon = new JLabel();
        JLabel description = new JLabel("", SwingConstants.CENTER);
        description.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 11));
        description.setForeground(foreground);

        if (o == null) {
            icon.setIcon(Utils.tailleImg(ConstsIcon.PLH_VIDE, this.SIZE, this.SIZE));
            icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 13, 0));
        }

        else if (o.getClass().equals(Clef.class)) {
            icon.setIcon(Utils.tailleImg(ConstsIcon.CLEF, this.SIZE, this.SIZE));
            String pre_text = "Clef";

            if (afficheDescription)
                pre_text = "";

            description.setText(pre_text + o.element.toString().toLowerCase());

            this.add(description);

        } else {
            switch (o.element) {
                case EAU:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.EAU, this.SIZE, this.SIZE));
                    break;
                case AIR:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.AIR, this.SIZE, this.SIZE));
                    break;
                case FEU:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.FEU, this.SIZE, this.SIZE));
                    break;
                case TERRE:
                    icon.setIcon(Utils.tailleImg(ConstsIcon.TERRE, this.SIZE, this.SIZE));
                    break;
                default:
                    break;
            }

            description.setText("Artefact: " + o.element.toString().toLowerCase());
            if (afficheDescription)
                this.add(description);

        }

        this.add(icon);
    }

    public VueObjet(Objet o) {
        this(o, new Color(0, 0, 0), true);
    }

    public VueObjet(Objet o, Color foreground) {
        this(o, foreground, true);
    }

    public VueObjet(Objet o, boolean afficheDescription) {
        this(o, new Color(0, 0, 0), afficheDescription);
    }
}
