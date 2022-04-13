package Vue;

import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class VueBouton extends JButton{
    public VueBouton(){
        this.setFont(new Font("Calibri", Font.BOLD, 15));
        this.setBackground(Color.decode("#f6f6f6"));
        this.setFocusable(false);
    }

    public VueBouton(String text){
        this();
        this.setText(text);
    }
    
    public VueBouton(Icon icon){
        this();
        this.setIcon(icon);
    }
}
