package Vue;

import java.awt.*;

import javax.swing.ImageIcon;

public final class Utils {
	public static GridBagConstraints positionGrid(int pos_x, int pos_y, int grid_width, int grid_height, int padding) {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.gridx = pos_x;
		gc.gridy = pos_y;
		gc.gridwidth = grid_width;
		gc.gridheight = grid_height;
		gc.insets = new Insets(padding, padding, padding, padding);

		return gc;
	}

	public static GridBagConstraints positionGrid(int pos_x, int pos_y, int padding) {
		return positionGrid(pos_x, pos_y, 1, 1, padding);
	}

	public static GridBagConstraints positionGrid(int pos_x, int pos_y) {
		return positionGrid(pos_x, pos_y, 1, 1, 0);
	}

	public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (icon.getIconWidth() > w) {
			width = w;
			height = (width * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (height > h) {
			height = h;
			width = (icon.getIconWidth() * height) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
}
