package Modele;

enum Element {
    AIR,
    EAU,
    TERRE,
    FEU;

    public static Element StringToElement(String s) {
        if (s.equals("AIR")) return Element.AIR;
        else if (s.equals("EAU")) return Element.EAU;
        else if (s.equals("TERRE")) return Element.TERRE;
        else if (s.equals("FEU")) return Element.FEU;
        else return null;
    }
}
