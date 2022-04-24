package Modele;

/**
 * Direction d'une case par rapport à la case d'un Joueur.
 */
public enum Direction {
    HAUT,
    BAS,
    GAUCHE,
    DROITE,
    /**
     * La case actuelle du Joueur.
     */
    NEUTRE;

    /**
     * @return Une chaine de caractères de la direction.
     */
    @Override
    public String toString() {
        switch (this) {
            case HAUT: return "le haut";
            case BAS: return "le bas";
            case GAUCHE: return "la gauche";
            case DROITE: return "la droite";
            case NEUTRE: return "neutre";
            default: throw new RuntimeException();
        }
    }
}
