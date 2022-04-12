package Modele;

public class Coord {
    private final int x, y;
    public Coord(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("");
        }
        this.x = x;
        this.y = y;
    }
    public int x() { return this.x; }
    public int y() { return this.y; }
    public String toString() { return String.format("(%d, %d)", this.x, this.y); }
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || (obj.getClass() != this.getClass())) { return false; }
        Coord c = (Coord) obj;
        return this.x == c.x() && this.y == c.y();
    }
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.y;
        return result;
    }
}
