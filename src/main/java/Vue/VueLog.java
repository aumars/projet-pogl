package Vue;

import javax.swing.*;
import java.awt.*;

public class VueLog extends JPanel {
    private JTextArea text_area = new JTextArea();
    private JScrollPane panel = new JScrollPane(text_area);

    public VueLog() {
        // this.setBackground(ConstsValue.COLOR_DEFAULT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(450, 60));        

        this.panel.getVerticalScrollBar().setBackground(ConstsValue.COLOR_DEFAULT);
        this.panel.getHorizontalScrollBar().setBackground(ConstsValue.COLOR_DEFAULT);
        this.panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        this.text_area.setFocusable(false);
        this.text_area.setEditable(false);
        
        // this.add(new JLabel(Utils.souligneLabel("Les informations :")));
        this.add(this.panel);
    }

    public void ajoutLog(String msg) {

        String text = String.format("- %s.", msg);
        this.text_area.append(text);
        this.text_area.revalidate();
    }
}
