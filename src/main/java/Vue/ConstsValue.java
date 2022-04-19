package Vue;

import java.awt.*;

/**
 * Contients toutes les valeurs constantes utilisé dans la VUE.
 */
public final class ConstsValue {
    // Les valeurs.
    public static int BOX_SIZE = 90;
    public static int GAP_CASE = 1;
    public static int BORDER_GRID = 10;

    // Les fonts
    public static String FONT_FAMILY = "Arial";

    // Les courleurs.
    public static Color COLOR_MER = new Color(47, 128, 124);
    public static Color COLOR_SEC = new Color(48, 97, 14);
    public static Color COLOR_INONDE = new Color(101, 147, 99);
    public static Color COLOR_DEFAULT = new Color(246, 246, 246);

    /**
     * Récupère le nom associé au joueurs avec l'id i.
     * 
     * @param i L'ID du joueurs.
     * @return
     */
    public static String getNomJoueur(int i) {
        String[] avatars = { "Jack", "Hector", "Ninja", "Pinky" };
        return avatars[i];
    }
}
