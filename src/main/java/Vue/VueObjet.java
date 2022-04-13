package Vue;

import javax.swing.*;
import java.awt.*;

public class VueObjet extends JLabel{
    public VueObjet(){
        this.setIcon(Utils.scaleImage(Constants.ICON_CLEF, 30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
