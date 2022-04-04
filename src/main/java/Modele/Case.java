package Modele;

public class Case {
    private final Coord coord;
    private final Terrain terrain;
    private final boolean helipad;
    private Inondation etat = Inondation.SECHE;
    private Objet objet;

    public Case(int i, int j, char c) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException(String.format("(i, j) = (%d, %d) doit avoir des composantes positives.", i, j));
        }
        this.coord = new Coord(i, j);
        switch (c) {
            case '-': this.terrain = Terrain.MER; this.helipad = false; break;
            case '*': this.terrain = Terrain.TERRE; this.helipad = false; break;
            case 'h': this.terrain = Terrain.TERRE; this.helipad = true; break;
            default: throw new IllegalArgumentException(String.format("Le caractère %s n'est pas reconnu.", c));
        }
    }

    public boolean estTraversable() {
        return this.terrain == Terrain.TERRE && this.etat != Inondation.SUBMERGEE;
    }

    void ajoutObjet(Objet o) {
        this.objet = o;
    }
}
