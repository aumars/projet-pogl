package Vue;

import java.awt.Font;

import javax.swing.*;
import Modele.*;

public class VueObjet extends JPanel {
    private final int SIZE = 40;

    public VueObjet(Objet o) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel icon = new JLabel();
        JLabel description = new JLabel("", SwingConstants.CENTER);
        description.setFont(new Font("", Font.ITALIC, 11));

        if (o == null)
            icon.setIcon(Utils.scaleImg(Constants.ICN_EMPTY, this.SIZE, this.SIZE));

        else if (o.getClass().equals(Clef.class)) {
            icon.setIcon(Utils.scaleImg(Constants.ICN_CLEF, this.SIZE, this.SIZE));
            description.setText("Clef: " + o.element.toString().toLowerCase());

        } else {
            switch (o.element) {
                case EAU:
                    icon.setIcon(Utils.scaleImg(Constants.ICN_EAU, this.SIZE, this.SIZE));
                    break;
                case AIR:
                    icon.setIcon(Utils.scaleImg(Constants.ICN_AIR, this.SIZE, this.SIZE));
                    break;
                case FEU:
                    icon.setIcon(Utils.scaleImg(Constants.ICN_FEU, this.SIZE, this.SIZE));
                    break;
                case TERRE:
                    icon.setIcon(Utils.scaleImg(Constants.ICN_TERRE, this.SIZE, this.SIZE));
                    break;
                default:
                    break;
            }

            description.setText("Artefact: " + o.element.toString().toLowerCase());
        }

        this.add(icon);
        this.add(description);
    }
}
