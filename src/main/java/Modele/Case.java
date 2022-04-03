package Modele;

public class Case {
    private final boolean helipad;
    private Objet objet;
    private int positionX;
    private int positionY;
    private Inondation etat = Inondation.SECHE;
    private final Terrain terrain;

    public Case(int i, int j, boolean m, boolean h) {
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
