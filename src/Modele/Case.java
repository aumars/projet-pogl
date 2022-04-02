package Modele;

public class Case {
    private boolean helicopter;
    private Objet objet;
    private int positionX;
    private int positionY;
    private Inondation etat = Inondation.SECHE;
    private final Terrain terrain;

    public Case(int i, int j, boolean m) {
        this.positionX = i;
        this.positionY = j;
        if (m) {
            this.terrain = Terrain.TERRE;
        }
        else {
            this.terrain = Terrain.MER;
        }
    }

    public boolean estTraversable() {
        return this.terrain == Terrain.TERRE && this.etat != Inondation.SUBMERGEE;
    }
}
