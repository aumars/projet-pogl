package Vue;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Contient des fonctions utiles pour la VUE.
 */
public final class Utils {
	/**
	 * Permets de positionner un element dans un GridBagLayout.
	 * 
	 * @param pos_x  la position en x de l'element dans la grille parent.
	 * @param pos_y  la position en y de l'element dans la grille parent.
	 * @param width  la longeur qu'occupe l'element dans la grille parents.
	 * @param height la largeur qu'occupe l'element dans la grille parents.
	 * @param pad    l'épaisseur de la marge qui entoure l'éléments.
	 * @return Un GridBagLayout qui permet de positionner l'element dans la grille.
	 */
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

	/**
	 * Permets de positionner un element dans un GridBagLayout.
	 * 
	 * @param pos_x la position en x de l'element dans la grille parent.
	 * @param pos_y la position en y de l'element dans la grille parent.
	 * @param pad   l'épaisseur de la marge qui entoure l'éléments.
	 * @return Un GridBagLayout qui permet de positionner l'element dans la grille.
	 */
	public static GridBagConstraints positionneGrille(int pos_x, int pos_y, int pad) {
		return positionneGrille(pos_x, pos_y, 1, 1, pad);
	}

	/**
	 * Permets de positionner un element dans un GridBagLayout.
	 * 
	 * @param pos_x la position en x de l'element dans la grille parent.
	 * @param pos_y la position en y de l'element dans la grille parent.
	 * @return Un GridBagLayout qui permet de positionner l'element dans la grille.
	 */
	public static GridBagConstraints positionneGrille(int pos_x, int pos_y) {
		return positionneGrille(pos_x, pos_y, 1, 1, 0);
	}

	/**
	 * Redimensionne une image sans perdre son ratio.
	 * 
	 * @param icon   L'image à redimensionner.
	 * @param size_w La longeur de l'image.
	 * @param size_h La largeur de l'image.
	 * @return l'image redimensionner.
	 */
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

	/**
	 * Ajoute les balises html pour souligner le text.
	 * 
	 * @param text Le texte à souligner.
	 * @return le texte avec les balises html de soulignements.
	 */
	public static String souligneLabel(String text) {
		return "<html><u>" + text + "</u></html>";
	}
}
