package Vue;

import javax.swing.*;
import java.awt.*;
import Modele.*;

public class VueObjet extends JLabel {
    private final int SIZE = 30;

    public VueObjet(Objet o) {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (o.getClass().equals(Clef.class)) {
            this.setIcon(Utils.scaleImage(Constants.ICON_CLEF, this.SIZE, this.SIZE));

        } else {
            switch (o.element) {
                case EAU:
                    this.setIcon(Utils.scaleImage(Constants.ICON_EAU, this.SIZE, this.SIZE));
                    break;
                case AIR:
                    this.setIcon(Utils.scaleImage(Constants.ICON_AIR, this.SIZE, this.SIZE));
                    break;
                case FEU:
                    this.setIcon(Utils.scaleImage(Constants.ICON_FEU, this.SIZE, this.SIZE));
                    break;
                case TERRE:
                    this.setIcon(Utils.scaleImage(Constants.ICON_TERRE, this.SIZE, this.SIZE));
                    break;
                default:
                    break;
            }

        }
    }
}
