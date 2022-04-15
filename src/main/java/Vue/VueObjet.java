package Vue;

import javax.swing.*;
import Modele.*;

public class VueObjet extends JLabel {
    private final int SIZE = 30;

    public VueObjet(Objet o) {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (o.getClass().equals(Clef.class)) {
            this.setIcon(Utils.scaleImg(Constants.ICN_CLEF, this.SIZE, this.SIZE));

        } else {
            switch (o.element) {
                case EAU:
                    this.setIcon(Utils.scaleImg(Constants.ICN_EAU, this.SIZE, this.SIZE));
                    break;
                case AIR:
                    this.setIcon(Utils.scaleImg(Constants.ICN_AIR, this.SIZE, this.SIZE));
                    break;
                case FEU:
                    this.setIcon(Utils.scaleImg(Constants.ICN_FEU, this.SIZE, this.SIZE));
                    break;
                case TERRE:
                    this.setIcon(Utils.scaleImg(Constants.ICN_TERRE, this.SIZE, this.SIZE));
                    break;
                default:
                    break;
            }

        }
    }
}
