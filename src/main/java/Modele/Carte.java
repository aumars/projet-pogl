package Modele;

import java.util.Objects;
import java.util.Scanner;

public final class Carte {
    public final Terrain[][] map;
    Carte(String map_path) {
        Scanner map_reader = new Scanner(Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(map_path)));
        int hauteur = map_reader.nextInt();
        int largeur = map_reader.nextInt();
        map_reader.skip("\n");
        this.map = new Terrain[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            String line = map_reader.nextLine();
            for (int j = 0; j < largeur; j++) {
                this.map[i][j] = terrainByID(line.charAt(j));
            }
        }
    }

    private static Terrain terrainByID(char c) {
        switch (c) {
            case '-': return Terrain.MER;
            case '*': return Terrain.TERRE;
            case 'h': return Terrain.HELIPAD;
            default: throw new IllegalArgumentException(String.format("Le caractère %s n'est pas reconnu comme un terrain.", c));
        }
    }
}
