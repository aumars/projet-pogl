package Modele;

public class Case {
    private boolean helicopter;
    private boolean tresor;
    private int positionX;
    private int positionY;
    private Inondation etat = Inondation.SECHE;
    private Terrain terrain;

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
}
