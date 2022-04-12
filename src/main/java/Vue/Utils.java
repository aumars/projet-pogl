package Vue;
import java.awt.*;

public final class Utils {
    public static GridBagConstraints positionGrid(int pos_x, int pos_y, int grid_width, int grid_height) {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = pos_x;
        gc.gridy = pos_y;
        gc.gridwidth = grid_width;
        gc.gridheight = grid_height;

        return gc;
    }    
}
