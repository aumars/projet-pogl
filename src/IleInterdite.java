import Modele.Modele;
import Vue.*;

import java.awt.*;

public class IleInterdite {

    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considère la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Modele modele = new Modele();
            Vue vue = new Vue(modele);
        });
    }
}
