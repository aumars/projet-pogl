package Modele;

/**
 * L'élément associé à un objet.
 */
public enum Element {
    AIR,
    EAU,
    TERRE,
    FEU;

    /**
     * @return Une chaine de caractère de l'element.
     */
    @Override
    public String toString() {
        switch (this) {
            case AIR: return "air";
            case EAU: return "eau";
            case TERRE: return "terre";
            case FEU: return "feu";
            default: throw new RuntimeException();
        }
    }
}
