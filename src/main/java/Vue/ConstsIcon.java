package Vue;

import java.lang.reflect.Array;
import java.util.List;

import javax.swing.ImageIcon;

public final class ConstsIcon {
    public static ImageIcon HELICOPTERE = new ImageIcon("src/main/resources/img/helicopter.png");
    public static ImageIcon CLEF = new ImageIcon("src/main/resources/img/key.png");
    public static ImageIcon FLECHE_DROITE = new ImageIcon("src/main/resources/img/arrow-right.png");
    public static ImageIcon FLECHE_GAUCHE = new ImageIcon("src/main/resources/img/arrow-left.png");
    public static ImageIcon FLECHE_HAUT = new ImageIcon("src/main/resources/img/arrow-top.png");
    public static ImageIcon FLECHE_BAS = new ImageIcon("src/main/resources/img/arrow-vue_info_bas.png");
    public static ImageIcon AVATAR_0 = new ImageIcon("src/main/resources/img/pirate.png");
    public static ImageIcon AVATAR_1 = new ImageIcon("src/main/resources/img/knight.png");
    public static ImageIcon AVATAR_2 = new ImageIcon("src/main/resources/img/ninja.png");
    public static ImageIcon AVATAR_3 = new ImageIcon("src/main/resources/img/pinky.png");
    public static ImageIcon FEU = new ImageIcon("src/main/resources/img/fire.png");
    public static ImageIcon EAU = new ImageIcon("src/main/resources/img/water.png");
    public static ImageIcon TERRE = new ImageIcon("src/main/resources/img/ground.png");
    public static ImageIcon AIR = new ImageIcon("src/main/resources/img/oxygen.png");
    public static ImageIcon TOMBE = new ImageIcon("src/main/resources/img/tombstone.png");
    public static ImageIcon SCEAU = new ImageIcon("src/main/resources/img/bucket.png");
    public static ImageIcon FIN_TOUR = new ImageIcon("src/main/resources/img/end-round.png");
    public static ImageIcon RECHERCHER = new ImageIcon("src/main/resources/img/search.png");
    public static ImageIcon PLH_VIDE = new ImageIcon("src/main/resources/img/empty.png");

    public static ImageIcon getImgAvatar(int i) {
        ImageIcon[] avatars = { AVATAR_0, AVATAR_1, AVATAR_2, AVATAR_3 };
        return avatars[i];
    }
}
