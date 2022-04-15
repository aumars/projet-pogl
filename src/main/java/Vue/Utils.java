package Vue;

import java.awt.*;
import javax.swing.ImageIcon;

public final class Utils {
	public static GridBagConstraints positionneGrille(int pos_x, int pos_y, int width, int height, int pad) {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.gridx = pos_x;
		gc.gridy = pos_y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.insets = new Insets(pad, pad, pad, pad);

		return gc;
	}

	public static GridBagConstraints positionneGrille(int pos_x, int pos_y, int pad) {
		return positionneGrille(pos_x, pos_y, 1, 1, pad);
	}

	public static GridBagConstraints positionneGrille(int pos_x, int pos_y) {
		return positionneGrille(pos_x, pos_y, 1, 1, 0);
	}

	public static ImageIcon tailleImg(ImageIcon icon, int size_w, int size_h) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (icon.getIconWidth() > size_w) {
			width = size_w;
			height = (width * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (height > size_h) {
			height = size_h;
			width = (icon.getIconWidth() * height) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	public static String souligneLabel(String text){
		return "<html><u>" + text + "</u></html>";
	}
}
