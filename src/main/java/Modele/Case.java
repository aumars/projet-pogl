package Modele;

public class Case {
    private final int positionX;
    private final int positionY;
    private final Terrain terrain;
    private final boolean helipad;
    private Inondation etat = Inondation.SECHE;
    private Objet objet;

    public Case(int i, int j, boolean m, boolean h) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException(String.format("(i, j) = (%d, %d) doit avoir des composantes positives.", i, j));
        }
        this.positionX = i;
        this.positionY = j;
        if (m) {
            this.terrain = Terrain.TERRE;
        }
        else {
            this.terrain = Terrain.MER;
        }
        this.helipad = h;
    }

    public boolean estTraversable() {
        return this.terrain == Terrain.TERRE && this.etat != Inondation.SUBMERGEE;
    }

    void ajoutObjet(Objet o) {
        this.objet = o;
    }
}
