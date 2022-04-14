package Vue;

import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class VueBouton extends JButton{
    public VueBouton(String tooltip){
        this.setFont(new Font("", Font.PLAIN, 18));
        this.setBackground(Color.decode("#f6f6f6"));
        this.setFocusable(false);
        this.setToolTipText(tooltip);
    }
    
    public VueBouton(String tooltip, String text){
        this(tooltip);
        this.setText(text);
    }
    
    public VueBouton(String tooltip, Icon icon){
        this(tooltip);
        this.setIcon(icon);
    }
    
    public VueBouton(String tooltip, String text, Icon icon){
        this(tooltip);
        this.setIcon(icon);
        this.setText(text);
    }
}
