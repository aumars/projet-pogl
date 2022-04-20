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

    // Les couleurs.
    public static Color COLOR_MER = new Color(47, 128, 124);
    public static Color COLOR_SEC = new Color(48, 97, 14);
    public static Color COLOR_INONDE = new Color(101, 147, 99);
    public static Color COLOR_DEFAULT = new Color(238, 238, 238);

    // Textes
    public static String TEXTE_REGLE = new String("L'île Interdite, parviendrez-vous à vous y échapper ?" +
            "\nParcourez les cases du plateau pour trouver les clefs, " +
            "puis ouvrez les trésors\nd'artefact qui y sont associés." +
            " Une fois les quatre artefacts récupérés, il ne\nreste plus" +
            " qu'à vous échapper avec l'hélicoptère. ");
}
